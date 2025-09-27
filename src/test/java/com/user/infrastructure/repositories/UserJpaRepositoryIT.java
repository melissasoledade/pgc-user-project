package com.user.infrastructure.repositories;

import com.user.domain.entities.User;
import com.user.fixtures.domain.AddressHelper;
import com.user.fixtures.domain.UserHelper;
import com.user.fixtures.domain.UserPreferencesHelper;
import com.user.fixtures.domain.UserProfilesHelper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserJpaRepositoryIT {

    @Autowired
    private UserJpaRepository repository;

    @BeforeEach
    void setup() {
        this.repository.deleteAll();
    }

    @Transactional
    @Test
    void shouldSaveUser() throws ParseException {
        // given
        final User user = UserHelper.defaultUser()
                .id(null)
                .address(AddressHelper.defaultAddress()
                        .addressId(null)
                        .build())
                .userProfiles(UserProfilesHelper.defaultUserProfiles()
                        .userProfilesId(null)
                        .build())
                .userPreferences(UserPreferencesHelper.defaultUserPreferences()
                        .userPreferencesId(null)
                        .build())
                .build();

        // when
        final User result = this.repository.saveUser(user);

        // then
        assertNotNull(result.getId());
        assertEquals(user.getName(), result.getName());
        assertNotNull(result.getAddress().getAddressId());
        assertEquals(user.getAddress().getNumber(), result.getAddress().getNumber());
        assertNotNull(result.getUserProfiles().getUserProfilesId());
        assertEquals(user.getUserProfiles().getProfileName(), result.getUserProfiles().getProfileName());
        assertNotNull(result.getUserPreferences().getUserPreferencesId());
        assertEquals(user.getUserPreferences().getTimeZone(), result.getUserPreferences().getTimeZone());
    }

    @Transactional
    @Test
    void shouldSaveMoreThanOneUser() throws ParseException {
        // given
        final User firstUser = UserHelper.defaultUser()
                .id(null)
                .address(AddressHelper.defaultAddress()
                        .addressId(null)
                        .build())
                .userProfiles(UserProfilesHelper.defaultUserProfiles()
                        .userProfilesId(null)
                        .build())
                .userPreferences(UserPreferencesHelper.defaultUserPreferences()
                        .userPreferencesId(null)
                        .build())
                .build();
        final User secondUser = UserHelper.defaultUser()
                .id(null)
                .name("Beatriz Santos")
                .cpf("88888888888")
                .address(AddressHelper.defaultAddress()
                        .addressId(null)
                        .build())
                .userProfiles(UserProfilesHelper.defaultUserProfiles()
                        .userProfilesId(null)
                        .build())
                .userPreferences(UserPreferencesHelper.defaultUserPreferences()
                        .userPreferencesId(null)
                        .build())
                .build();

        // when
        final User firstUserSaved = this.repository.saveUser(firstUser);
        final User secondUserSaved = this.repository.saveUser(secondUser);

        // then
        assertNotNull(firstUserSaved.getId());
        assertNotNull(secondUserSaved.getId());
        assertEquals("Ana da Silva", firstUserSaved.getName());
        assertEquals("Beatriz Santos", secondUserSaved.getName());
        assertNotNull(firstUserSaved.getAddress().getAddressId());
        assertNotNull(secondUserSaved.getAddress().getAddressId());
    }

    @Transactional
    @Test
    void shouldFindUserById() throws ParseException {
        // given
        final User user = UserHelper.defaultUser()
                .id(null)
                .address(AddressHelper.defaultAddress()
                        .addressId(null)
                        .build())
                .userProfiles(UserProfilesHelper.defaultUserProfiles()
                        .userProfilesId(null)
                        .build())
                .userPreferences(UserPreferencesHelper.defaultUserPreferences()
                        .userPreferencesId(null)
                        .build())
                .build();

        // when
        final User savedUser = this.repository.saveUser(user);
        final Optional<User> result = this.repository.findUserById(savedUser.getId());

        // then
        assertNotNull(result.get().getId());
        assertEquals("Ana da Silva", result.get().getName());
        assertEquals("SP", result.get().getAddress().getState());
        assertEquals("tenant", result.get().getUserProfiles().getProfileName());
        assertEquals(Boolean.FALSE, result.get().getUserPreferences().getWhatsAppPromotional());
        assertNotNull(result.get().getAddress().getAddressId());
        assertNotNull(result.get().getUserPreferences().getUserPreferencesId());
        assertNotNull(result.get().getUserProfiles().getUserProfilesId());
    }

    @Transactional
    @Test
    void shouldNotFindUserByIdWhenIsNotSaved() throws ParseException {
        // given
        final User user = UserHelper.defaultUser()
                .id(1L)
                .address(AddressHelper.defaultAddress()
                        .addressId(null)
                        .build())
                .userProfiles(UserProfilesHelper.defaultUserProfiles()
                        .userProfilesId(null)
                        .build())
                .userPreferences(UserPreferencesHelper.defaultUserPreferences()
                        .userPreferencesId(null)
                        .build())
                .build();

        // when
        final Optional<User> result = this.repository.findUserById(1L);

        // then
        assertThat(result).isEmpty();
    }

    @Transactional
    @Test
    void shouldFindUsersById() throws ParseException {
        // given
        final User firstUser = UserHelper.defaultUser()
                .id(null)
                .address(AddressHelper.defaultAddress()
                        .addressId(null)
                        .build())
                .userProfiles(UserProfilesHelper.defaultUserProfiles()
                        .userProfilesId(null)
                        .build())
                .userPreferences(UserPreferencesHelper.defaultUserPreferences()
                        .userPreferencesId(null)
                        .build())
                .build();
        final User secondUser = UserHelper.defaultUser()
                .id(null)
                .name("Beatriz Santos")
                .cpf("88888888888")
                .address(AddressHelper.defaultAddress()
                        .addressId(null)
                        .build())
                .userProfiles(UserProfilesHelper.defaultUserProfiles()
                        .userProfilesId(null)
                        .build())
                .userPreferences(UserPreferencesHelper.defaultUserPreferences()
                        .userPreferencesId(null)
                        .build())
                .build();

        final User savedFirstUser = this.repository.saveUser(firstUser);
        final User savedSecondUser = this.repository.saveUser(secondUser);

        // when
        final List<User> users = this.repository.findUsersByIds(
                List.of(savedFirstUser.getId(), savedSecondUser.getId()));

        // then
        assertThat(users)
                .hasSize(2)
                .extracting(User::getId)
                .containsExactlyInAnyOrder(savedFirstUser.getId(), savedSecondUser.getId());

    }

    @Transactional
    @Test
    void shouldDeleteUserById() throws ParseException {
        // given
        final User user = UserHelper.defaultUser()
                .id(null)
                .address(AddressHelper.defaultAddress()
                        .addressId(null)
                        .build())
                .userProfiles(UserProfilesHelper.defaultUserProfiles()
                        .userProfilesId(null)
                        .build())
                .userPreferences(UserPreferencesHelper.defaultUserPreferences()
                        .userPreferencesId(null)
                        .build())
                .build();

        final User savedUser = this.repository.saveUser(user);

        // when
        this.repository.deleteUserById(savedUser.getId());
        final Optional<User> result = this.repository.findUserById(savedUser.getId());

        // then
        assertThat(result).isEmpty();
    }

}