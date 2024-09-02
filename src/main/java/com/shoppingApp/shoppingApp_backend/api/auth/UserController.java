package com.shoppingApp.shoppingApp_backend.api.auth;

import com.shoppingApp.shoppingApp_backend.dao.LocalUserDAO;
import com.shoppingApp.shoppingApp_backend.model.LocalUser;
import com.shoppingApp.shoppingApp_backend.service.LocalUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class UserController {
    private LocalUserService localUserService;
    private final LocalUserDAO localUserDAO;

    public UserController(LocalUserService localUserService,
                          LocalUserDAO localUserDAO) {
        this.localUserService = localUserService;
        this.localUserDAO = localUserDAO;
    }

    @GetMapping("/users")
    public List<LocalUser> getAllUsers() {
        return localUserService.getAllUsers();

    }

    @GetMapping("/users/{id}")
    public Optional<LocalUser> getUserById(@PathVariable Long id) {
        return localUserService.getUserById(id);
    }

    @DeleteMapping("/users/deleteById/{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable Long id) {localUserDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);


    }

    @PostMapping("/users/updateById/{id}")
    public ResponseEntity<LocalUser> updateUserById(@PathVariable Long id, @RequestBody LocalUser newUserData){
        Optional<LocalUser> oldUserData= localUserDAO.findById(id);
        if(oldUserData.isPresent()){
            LocalUser updatedUser = oldUserData.get();
            updatedUser.setUsername(newUserData.getUsername());
            updatedUser.setFirstname(newUserData.getFirstname());
            updatedUser.setLastname(newUserData.getLastname());
            updatedUser.setEmail(newUserData.getEmail());
            updatedUser.setPassword(newUserData.getPassword());

            LocalUser userObj = localUserDAO.save(updatedUser);
            return new ResponseEntity<LocalUser>(userObj, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
