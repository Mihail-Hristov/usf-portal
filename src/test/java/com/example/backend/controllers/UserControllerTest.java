package com.example.backend.controllers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.example.backend.model.entity.User;
import com.example.backend.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    private static final String TEST_USER_EMAIL = "misho@example.bg";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void testOpenRegistrationForm() throws Exception {
        mockMvc
                .perform(get("/portal/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    void testOpenLoginForm() throws Exception {
        mockMvc
                .perform(get("/portal/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void testRegisterUser() throws Exception {
        mockMvc.perform(post("/portal/users/register")
                .param("username", TEST_USER_EMAIL)
                .param("nickname", "Тъпана")
                .param("password", "12345678")
                .param("confirmPassword", "12345678")
                .param("firstName", "Mihail")
                .param("lastName", "Hristov")
                .param("isDriver", "true")
                .param("groupName", "USF")
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        ).
                andExpect(status().is3xxRedirection());

        Assertions.assertEquals(1, userRepository.count());

        Optional<User> newUserOpt = userRepository.findFirstByUsername(TEST_USER_EMAIL);

        Assertions.assertTrue(newUserOpt.isPresent());

        User newUser = newUserOpt.get();

        Assertions.assertEquals("Тъпана", newUser.getNickname());
        Assertions.assertEquals("Mihail", newUser.getFirstName());
        Assertions.assertEquals("Hristov", newUser.getLastName());
        Assertions.assertEquals("123", newUser.getPassword());

    }

    @Test
    void testLoginUser() throws Exception {
        mockMvc.perform(post("/portal/users/login")
                        .param("username", TEST_USER_EMAIL)
                        .param("password", "12345678")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                ).
                andExpect(status().isOk());
    }


}