package com.shoppingApp.shoppingApp_backend.security;


import com.shoppingApp.shoppingApp_backend.dao.LocalUserDAO;
import com.shoppingApp.shoppingApp_backend.model.LocalUser;
import com.shoppingApp.shoppingApp_backend.service.JWTService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest

@AutoConfigureMockMvc
public class JwtAuthenticationFilterTests {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private LocalUserDAO localUserDAO;

    private static final String AUTHENTICATED_PATH = "/auth/me";

    @Test
    public void testUnauthenticatedRequest() throws Exception {
        mvc.perform(get(AUTHENTICATED_PATH)).andExpect(status().is(HttpStatus.FORBIDDEN.value()));


    }

    @Test
    public void testBadToken() throws Exception {
        mvc.perform(get(AUTHENTICATED_PATH).header("Authorization", "BadToken"))
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
        mvc.perform(get(AUTHENTICATED_PATH).header("Authorization", "BearerBadToken/Invalid"))
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));
    }

    //If user exists
    @Test
    public void TestVerifiedUser() throws Exception {
        LocalUser user = localUserDAO.findByUsername("User1").get();
        String token = jwtService.generateToken(user);
        mvc.perform(get(AUTHENTICATED_PATH).header("Authorization", "Bearer " + token))
                .andExpect(status().is(HttpStatus.FORBIDDEN.value()));


    }

//    @Test
//    public void TestSuccessful() throws Exception {
//        LocalUser user = localUserDAO.findByUsername("User2").get();
//        String token = jwtService.generateToken(user);
//        mvc.perform(get(AUTHENTICATED_PATH).header("Authorization", "Bearer " + token))
//                .andExpect(status().is(HttpStatus.OK.value()));
//
//
//    }

}

