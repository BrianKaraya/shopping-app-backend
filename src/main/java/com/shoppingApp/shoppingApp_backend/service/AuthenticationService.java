package com.shoppingApp.shoppingApp_backend.service;

import com.shoppingApp.shoppingApp_backend.dao.LocalUserDAO;
import com.shoppingApp.shoppingApp_backend.dao.TokenRepository;
import com.shoppingApp.shoppingApp_backend.model.AuthenticationResponse;
import com.shoppingApp.shoppingApp_backend.model.LocalUser;
import com.shoppingApp.shoppingApp_backend.model.Token;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService {
    private final LocalUserDAO localUserDAO;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;


    public AuthenticationService(LocalUserDAO localUserDAO, PasswordEncoder passwordEncoder, JWTService jwtService, AuthenticationManager authenticationManager, TokenRepository tokenRepository) {
        this.localUserDAO = localUserDAO;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.tokenRepository = tokenRepository;
    }



    public AuthenticationResponse register(LocalUser request) {
        LocalUser user = new LocalUser();
        user.setUsername(request.getUsername());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        //user.setPassword(request.getPassword());
        user.setRole(request.getRole());

        user = localUserDAO.save(user);

        String jwt = jwtService.generateToken(user);

        //Save generated token
        saveUserToken(jwt, user);


        return new AuthenticationResponse(jwt);
    }


    public AuthenticationResponse authenticate(LocalUser request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()

                )
        );
        LocalUser user = localUserDAO.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);

        RevokeAllTokensByUser(user);

        saveUserToken(token, user);

        return new AuthenticationResponse(token);


    }

    private void RevokeAllTokensByUser(LocalUser user) {
        List<Token>validTokenListByUser=tokenRepository.findAllTokensByUser(user.getId());
        if (!validTokenListByUser.isEmpty()){
            validTokenListByUser.forEach(t -> {
                t.setLoggedOut(true);
            });
        }
        tokenRepository.saveAll(validTokenListByUser);
    }

    private void saveUserToken(String jwt, LocalUser user) {
        Token token = new Token();
        token.setToken(jwt);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepository.save(token);
    }
}
