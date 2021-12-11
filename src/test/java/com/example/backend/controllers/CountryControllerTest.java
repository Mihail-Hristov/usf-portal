package com.example.backend.controllers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.example.backend.repository.CountryRepository;
import com.example.backend.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CountryControllerTest {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;



    @WithMockUser(value = "misho@example.bg")
    @Test
    void testOpenCountryPageWithUserThatDoesNotHaveTripAdminRole() throws Exception {
        mockMvc
                .perform(get("/portal/countries/all"))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(value = "misho@example.bg", roles = {"TRIP_ADMIN"})
    @Test
    void testOpenCountryPageWithUserThatHaveTripAdminRole() throws Exception {
        mockMvc
                .perform(get("/portal/countries/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("countries"));
    }
}