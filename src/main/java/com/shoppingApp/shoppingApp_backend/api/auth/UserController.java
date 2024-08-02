package com.shoppingApp.shoppingApp_backend.api.auth;

import com.shoppingApp.shoppingApp_backend.model.LocalUser;
import com.shoppingApp.shoppingApp_backend.model.Product;
import com.shoppingApp.shoppingApp_backend.service.LocalUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class UserController {
    private LocalUserService localUserService;

    public UserController(LocalUserService localUserService) {
        this.localUserService = localUserService;
    }

    @GetMapping("/users")
    public List<LocalUser> getAllUsers() {
        return localUserService.getAllUsers();

    }

    @GetMapping("/users/{id}")
    public Optional<LocalUser> getUserById(@PathVariable Long id) {
        return localUserService.getUserById(id);
    }


}
