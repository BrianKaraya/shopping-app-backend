package com.shoppingApp.shoppingApp_backend.service;

import com.shoppingApp.shoppingApp_backend.dao.LocalUserDAO;
import com.shoppingApp.shoppingApp_backend.model.LocalUser;
import com.shoppingApp.shoppingApp_backend.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalUserService {
    @Autowired
    private LocalUserDAO localUserDAO;
    private JWTService jwtService;

    public LocalUserService(LocalUserDAO localUserDAO, JWTService jwtService) {
        this.localUserDAO = localUserDAO;
        this.jwtService = jwtService;
    }



    public List<LocalUser> getAllUsers(){
        return localUserDAO.findAll();
    }
    public Optional<LocalUser> getUserById(Long id){
        return localUserDAO.findById(id);

    }
    public Optional<LocalUser> getUserByUsername(String username){
        return localUserDAO.findByUsername(username);
    }



}
