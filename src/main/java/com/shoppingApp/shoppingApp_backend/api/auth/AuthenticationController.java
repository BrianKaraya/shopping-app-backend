package com.shoppingApp.shoppingApp_backend.api.auth;

import com.shoppingApp.shoppingApp_backend.exceptions.UserAlreadyExistsException;
import com.shoppingApp.shoppingApp_backend.model.AuthenticationResponse;
import com.shoppingApp.shoppingApp_backend.model.LocalUser;
import com.shoppingApp.shoppingApp_backend.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/auth")

public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody LocalUser request){
        return ResponseEntity.ok(authenticationService.register(request));


    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LocalUser request)  {
        return ResponseEntity.ok(authenticationService.authenticate(request));



    }

    @GetMapping("/admin_only")
    public ResponseEntity<String> adminOnly() {
        return ResponseEntity.ok("Admin only url");
    }

    @GetMapping("/me")
    public LocalUser getLoggedInUserProfile(@AuthenticationPrincipal LocalUser user) {
        return user;


    }

}
