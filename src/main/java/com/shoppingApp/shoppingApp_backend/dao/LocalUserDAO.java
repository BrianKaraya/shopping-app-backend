package com.shoppingApp.shoppingApp_backend.dao;

import com.shoppingApp.shoppingApp_backend.model.LocalUser;
import com.shoppingApp.shoppingApp_backend.model.Role;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

//Repository
public interface LocalUserDAO extends ListCrudRepository<LocalUser,Long> {




    Optional<LocalUser> findByUsername(String username);

    Optional<LocalUser> findByEmail(String email);

    @Override
    Optional<LocalUser> findById(Long aLong);

    // Optional<LocalUser> findByRoleIgnoreCase(Role role);

}
