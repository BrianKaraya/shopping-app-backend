package com.shoppingApp.shoppingApp_backend.dao;

import com.shoppingApp.shoppingApp_backend.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query("""
            select t from Token t inner join LocalUser u on t.user.id=u.id
            where t.user.id= :userId and t.loggedOut=false
            """)
    List<Token> findAllTokensByUser(Long userId);


    Optional<Token> findByToken(String token);
}
