package com.user.infrastructure.repositories;

import com.user.domain.entities.User;
import com.user.domain.entities.UserProfiles;
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
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class UserProfilesRepositoryIT {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfilesRepository userProfilesRepository;

    @BeforeEach
    void setup() {
        this.userRepository.deleteAll();
    }

    @Transactional
    @Test
    void shouldFindUserProfilesByUserId() throws ParseException {
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

        final User savedUser = this.userRepository.saveUser(user);

        // when
        final Optional<UserProfiles> userProfiles = this.userProfilesRepository
                .findUserProfilesByUserId(savedUser.getId());

        assertThat(userProfiles)
                .isPresent()
                .satisfies(content -> {
                    assertThat(content.get().getProfileName()).isEqualTo("tenant");
                    assertThat(content.get().getProfileCode()).isNotNull();
                });
    }

    @Transactional
    @Test
    void shouldNotFindUserProfilesWhenUserIsNotSaved() {
        // when
        final Optional<UserProfiles> userProfiles = this.userProfilesRepository
                .findUserProfilesByUserId(4L);

        // then
        assertThat(userProfiles).isEmpty();
    }

}