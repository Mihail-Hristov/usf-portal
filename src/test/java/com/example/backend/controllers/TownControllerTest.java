package com.example.backend.controllers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.example.backend.model.entity.Country;
import com.example.backend.model.entity.User;
import com.example.backend.repository.CountryRepository;
import com.example.backend.repository.TownRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.UserRoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
public class TownControllerTest {

    private User testUser;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TownRepository townRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

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
        this.townRepository.deleteAll();
    }


    @WithMockUser(value = "misho@example.bg")
    @Transactional
    @Test
    void testOpenTownPageWithUserThatDoesNotHaveTripAdminRole() throws Exception {
        mockMvc
                .perform(get("/portal/towns/all"))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(value = "misho@example.bg", roles = {"TRIP_ADMIN"})
    @Transactional
    @Test
    void testOpenTownPageWithUserThatHaveTripAdminRole() throws Exception {
        mockMvc
                .perform(get("/portal/towns/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("towns"));
    }

    @WithMockUser(value = "misho@example.bg", roles = {"TRIP_ADMIN"})
    @Transactional
    @Test
    void testCreateNewTown() throws Exception {

        Country country = new Country();
        country.setName("България");
        countryRepository.save(country);

        mockMvc
                .perform(post("/portal/towns/create")
                        .param("name", "Бургас")
                        .param("desinationCountry", country.getId())
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());


    }


}
