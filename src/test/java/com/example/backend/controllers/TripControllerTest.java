package com.example.backend.controllers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.example.backend.model.binding.CreateTripBindingModel;
import com.example.backend.model.entity.User;
import com.example.backend.repository.TripRepository;
import com.example.backend.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ModelAttribute;

import static org.junit.jupiter.api.Assertions.*;

@WithMockUser("misho@example.bg")
@SpringBootTest
@AutoConfigureMockMvc
class TripControllerTest {

    private User testUser;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute
    private CreateTripBindingModel addTripBindingModel() {
        return new CreateTripBindingModel();
    }

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

    @AfterEach
    void tearDown() {
        tripRepository.deleteAll();
    }

    @Test
    void testOpenTripsPage() throws Exception {
        mockMvc
                .perform(get("/portal/trips"))
                .andExpect(status().isOk())
                .andExpect(view().name("trips"));
    }

}