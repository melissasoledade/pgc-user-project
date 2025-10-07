package com.user.application.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.application.services.UserProfilesService;
import com.user.domain.entities.User;
import com.user.domain.repositories.BaseUserRepository;
import com.user.fixtures.domain.AddressHelper;
import com.user.fixtures.domain.UserHelper;
import com.user.fixtures.domain.UserPreferencesHelper;
import com.user.fixtures.domain.UserProfilesHelper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class RestUserProfilesControllerIT {

    @Autowired
    private UserProfilesService service;

    @Autowired
    private BaseUserRepository repository;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;

    @BeforeEach
    void setup() throws ParseException {
        user = UserHelper.defaultUser()
                .id(null)
                .address(AddressHelper.defaultAddress().
                        addressId(null).build())
                .userPreferences(UserPreferencesHelper.defaultUserPreferences()
                        .userPreferencesId(null).build())
                .userProfiles(UserProfilesHelper.defaultUserProfiles()
                        .userProfilesId(null).build()).build();
        this.repository.saveUser(user);
    }

    @Test
    void shouldGetUserProfilesById() throws Exception {
        // when & then
        this.mvc.perform(get("/user/profiles/" + user.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.profileCode").value("2"))
                .andExpect(jsonPath("$.profileName").value("tenant"));
    }

}