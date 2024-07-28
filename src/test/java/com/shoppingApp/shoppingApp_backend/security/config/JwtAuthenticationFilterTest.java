package com.shoppingApp.shoppingApp_backend.security.config;

import com.shoppingApp.shoppingApp_backend.dao.LocalUserDAO;
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
public class JwtAuthenticationFilterTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private LocalUserDAO localUserDAO;

    private static final String AUTHENTICATED_PATH="/me";
    //@Test
//    public void testUnauthenticatedRequest()  throws Exception {
//        mvc.perform(get(AUTHENTICATED_PATH)).andExpect(status().is(HttpStatus.FORBIDDEN.value()));
//
//    }
}
