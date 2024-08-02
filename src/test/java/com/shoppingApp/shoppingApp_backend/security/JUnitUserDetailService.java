package com.shoppingApp.shoppingApp_backend.security;

import com.shoppingApp.shoppingApp_backend.dao.LocalUserDAO;
import com.shoppingApp.shoppingApp_backend.model.LocalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JUnitUserDetailService implements UserDetailsService {
    @Autowired
    private LocalUserDAO localUserDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<LocalUser> opUser = localUserDAO.findByUsername(username);
        return opUser.orElse(null);
    }
}
