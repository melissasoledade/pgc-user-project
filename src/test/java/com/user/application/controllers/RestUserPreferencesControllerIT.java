package com.user.application.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.application.services.UserPreferencesService;
import com.user.domain.entities.User;
import com.user.domain.repositories.BaseUserRepository;
import com.user.fixtures.domain.AddressHelper;
import com.user.fixtures.domain.UserHelper;
import com.user.fixtures.domain.UserPreferencesHelper;
import com.user.fixtures.domain.UserProfilesHelper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class RestUserPreferencesControllerIT {

    @Autowired
    private UserPreferencesService service;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BaseUserRepository repository;

    @Test
    void shouldGetUserByIdWithSuccess() throws Exception {
        // given
        final User user = UserHelper.defaultUser()
                .id(null)
                .address(AddressHelper.defaultAddress().
                        addressId(null).build())
                .userPreferences(UserPreferencesHelper.defaultUserPreferences()
                        .userPreferencesId(null).build())
                .userProfiles(UserProfilesHelper.defaultUserProfiles()
                        .userProfilesId(null).build())
                .build();
        this.repository.saveUser(user);

        // when & then
        this.mvc.perform(get("/user/preferences/" + 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.language").value("PT-BR"))
                .andExpect(jsonPath("$.whatsAppOptIn").value("true"))
                .andExpect(jsonPath("$.emailOptIn").value("false"));
    }
}