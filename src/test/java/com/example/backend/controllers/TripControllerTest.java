package com.example.backend.controllers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.example.backend.model.binding.CreateTripBindingModel;
import com.example.backend.model.entity.Country;
import com.example.backend.model.entity.Town;
import com.example.backend.model.entity.User;
import com.example.backend.repository.CountryRepository;
import com.example.backend.repository.TownRepository;
import com.example.backend.repository.TripRepository;
import com.example.backend.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class TripControllerTest {

    private User testUser;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TownRepository townRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private UserRepository userRepository;

    @ModelAttribute
    private CreateTripBindingModel addTripBindingModel() {
        return new CreateTripBindingModel();
    }


    @BeforeEach
    void loginUser() throws Exception {
        testUser = new User();
        testUser.setUsername("misho2@example.bg");
        testUser.setPassword("12345678");
        testUser.setNickname("????????????");
        testUser.setFirstName("Mihail");
        testUser.setLastName("Hristov");

        userRepository.save(testUser);
    }


    @WithMockUser(value = "misho@example.bg", roles = {"TRIP_ADMIN"})
    @Transactional
    @Test
    void testOpenTripsPageWithAuthenticatedUser() throws Exception {
        mockMvc
                .perform(get("/portal/trips"))
                .andExpect(status().isOk())
                .andExpect(view().name("trips"));
    }

    @WithAnonymousUser
    @Test
    void testOpenTripsPageWithNotAuthenticatedUser() throws Exception {
        mockMvc
                .perform(get("/portal/trips"))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(value = "misho@example.bg", roles = {"TRIP_ADMIN"})
    @Transactional
    @Test
    void testCreateNewTrip() throws Exception {

        Country country = new Country();
        country.setName("????????????????");
        countryRepository.save(country);

        Town town = new Town();
        town.setName("??????????????");
        town.setCountry(country);
        townRepository.save(town);

        mockMvc
                .perform(post("/portal/trips/create")
                        .param("name", "Test name for trip")
                        .param("destinationTown", town.getId())
                        .param("destinationCountry", country.getId())
                        .param("matchDay", "01.01.2022")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());
    }

}