package com.example.backend.controllers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.example.backend.model.entity.User;
import com.example.backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {

    private User testUser;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void loginUser() throws Exception {
        testUser = new User();
        testUser.setUsername("misho@example.bg");
        testUser.setPassword("12345678");
        testUser.setNickname("Тъпана");
        testUser.setFirstName("Mihail");
        testUser.setLastName("Hristov");

        userRepository.save(testUser);
    }

    @WithMockUser(value = "misho@example.bg")
    @Transactional
    @Test
    void testOpenHomePageWithAuthenticatedUser() throws Exception {
        mockMvc
                .perform(get("/portal"))
                .andExpect(status().isOk())
                .andExpect(view().name("portal-home"));
    }

    @WithAnonymousUser
    @Test
    void testOpenHomePageWithNotAuthenticatedUser() throws Exception {
        mockMvc
                .perform(get("/portal"))
                .andExpect(status().is3xxRedirection());
    }

}