package com.shoppingApp.shoppingApp_backend.security.config;

import com.shoppingApp.shoppingApp_backend.dao.TokenRepository;
import com.shoppingApp.shoppingApp_backend.model.Token;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomLogoutHandler implements LogoutHandler {
    private final TokenRepository tokenRepository;

    public CustomLogoutHandler(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }


    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")){

            return;
        }
        String token = authHeader.substring(7);

        //Get stored token from db
        Token storedToken = tokenRepository.findByToken(token).orElse(null);
        // and invalidate token
        assert storedToken != null;
        storedToken.setLoggedOut(true);
        tokenRepository.save(storedToken);


    }
}
