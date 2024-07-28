package com.shoppingApp.shoppingApp_backend.service;

import com.shoppingApp.shoppingApp_backend.dao.LocalUserDAO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final LocalUserDAO localUserDAO;

    public UserDetailsServiceImpl(LocalUserDAO localUserDAO) {
        this.localUserDAO = localUserDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return localUserDAO.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(
                "User not found"));
    }
}
