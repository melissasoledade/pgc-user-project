package com.user.application.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.application.dto.request.UserDTO;
import com.user.application.services.UserService;
import com.user.domain.entities.User;
import com.user.domain.repositories.BaseUserRepository;
import com.user.fixtures.application.request.UserDTOHelper;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RestUserControllerIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService service;

    @Autowired
    private BaseUserRepository repository;

    private User firstUserSaved;
    private User secondUserSaved;
    private User thirdUserSaved;

    @BeforeEach
    void setup() throws ParseException {
        final User firstUser = UserHelper.defaultUser()
                .id(null)
                .address(AddressHelper.defaultAddress().
                        addressId(null).build())
                .userPreferences(UserPreferencesHelper.defaultUserPreferences()
                        .userPreferencesId(null).build())
                .userProfiles(UserProfilesHelper.defaultUserProfiles()
                        .userProfilesId(null).build()).build();

        final User secondUser = UserHelper.defaultUser()
                .id(null)
                .name("Paulo Dias")
                .email("paulo.dias@gmail.com")
                .phoneNumber("+5511976656565")
                .gender("masculine")
                .address(AddressHelper.defaultAddress().
                        addressId(null).build())
                .userPreferences(UserPreferencesHelper.defaultUserPreferences()
                        .userPreferencesId(null).build())
                .userProfiles(UserProfilesHelper.defaultUserProfiles()
                        .userProfilesId(null).build()).build();

        final User thirdUser = UserHelper.defaultUser()
                .id(null)
                .name("Beatriz Santos")
                .email("bia.santos@gmail.com")
                .phoneNumber("+5511983737474")
                .gender("feminine")
                .address(AddressHelper.defaultAddress().
                        addressId(null).build())
                .userPreferences(UserPreferencesHelper.defaultUserPreferences()
                        .userPreferencesId(null).build())
                .userProfiles(UserProfilesHelper.defaultUserProfiles()
                        .userProfilesId(null).build()).build();

        this.repository.deleteAll();
        firstUserSaved = this.repository.saveUser(firstUser);
        secondUserSaved = this.repository.saveUser(secondUser);
        thirdUserSaved = this.repository.saveUser(thirdUser);
    }

    @Transactional
    @Test
    void shouldGetUserByIdWithSuccess() throws Exception {
        // when & then
        this.mvc.perform(get("/user/{id}", firstUserSaved.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(firstUserSaved.getId().toString()))
                .andExpect(jsonPath("$.name").value("Ana da Silva"))
                .andExpect(jsonPath("$.gender").value("feminine"));
    }

    @Transactional
    @Test
    void shouldGetUsersByIdWithSuccess() throws Exception {
        // when & then
        this.mvc.perform(get("/user/list")
                        .param("ids",
                                firstUserSaved.getId().toString(),
                                secondUserSaved.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]userId").value(firstUserSaved.getId().toString()))
                .andExpect(jsonPath("$.[0]name").value("Ana da Silva"))
                .andExpect(jsonPath("$.[0]gender").value("feminine"))
                .andExpect(jsonPath("$.[1]userId").value(secondUserSaved.getId().toString()))
                .andExpect(jsonPath("$.[1]name").value("Paulo Dias"))
                .andExpect(jsonPath("$.[1]gender").value("masculine"));
    }

    @Transactional
    @Test
    void shouldThrowUserNotFoundException() throws Exception {
        // when & then
        this.mvc.perform(get("/user/{id}", "9999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errors[0]").value("User not found."));
    }

    @Transactional
    @Test
    void shouldCreateUserWithSuccess() throws Exception {
        // given
        final UserDTO userDTO = UserDTOHelper.defaultUserDTO()
                .name("Bruna Pereira")
                .email("bruna.pereira@gmail.com")
                .build();

        // when & then
        this.mvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").isNumber())
                .andExpect(jsonPath("$.name").value("Bruna Pereira"))
                .andExpect(jsonPath("$.email").value("bruna.pereira@gmail.com"))
                .andExpect(jsonPath("$.gender").value("feminine"))
                .andExpect(jsonPath("$.address").exists())
                .andExpect(jsonPath("$.userPreferences").exists())
                .andExpect(jsonPath("$.userProfiles").exists());
    }

    @Transactional
    @Test
    void shouldUpdateUserWithSuccess() throws Exception {
        // given
        final UserDTO userDTO = UserDTOHelper.defaultUserDTO()
                .email("ana-novo-email@gmail.com")
                .build();

        // when & then
        this.mvc.perform(put("/user/{id}", firstUserSaved.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").isNumber())
                .andExpect(jsonPath("$.name").value("Ana da Silva"))
                .andExpect(jsonPath("$.email").value("ana-novo-email@gmail.com"))
                .andExpect(jsonPath("$.gender").value("feminine"))
                .andExpect(jsonPath("$.address").exists())
                .andExpect(jsonPath("$.userPreferences").exists())
                .andExpect(jsonPath("$.userProfiles").exists());
    }

    @Transactional
    @Test
    void shouldDeleteUserById() throws Exception {
        // when & then
        this.mvc.perform(delete("/user/{id}", thirdUserSaved.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(thirdUserSaved.getId().toString()))
                .andExpect(jsonPath("$.name").value("Beatriz Santos"))
                .andExpect(jsonPath("$.gender").value("feminine"));

    }


}