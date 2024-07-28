package com.shoppingApp.shoppingApp_backend.auth;

import com.shoppingApp.shoppingApp_backend.dao.LocalUserDAO;
import com.shoppingApp.shoppingApp_backend.model.AuthenticationResponse;
import com.shoppingApp.shoppingApp_backend.model.LocalUser;
import com.shoppingApp.shoppingApp_backend.service.AuthenticationService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {
    @Autowired
    private MockMvc mvc;
    private static final String GOOD_REGISTRATION_JSON = "{\"}";
    @Autowired
    private LocalUserDAO localUserDAO;

    @Test
    @Transactional
    public void testRegister() throws Exception {






}
}
