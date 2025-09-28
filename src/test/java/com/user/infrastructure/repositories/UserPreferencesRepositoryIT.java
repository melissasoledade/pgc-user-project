package com.user.infrastructure.repositories;

import com.user.domain.entities.User;
import com.user.domain.entities.UserPreferences;
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
class UserPreferencesRepositoryIT {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPreferencesRepository userPreferencesRepository;

    @BeforeEach
    void setup() {
        this.userRepository.deleteAll();
    }

    @Transactional
    @Test
    void shouldFindUserPreferencesById() throws ParseException {
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
        final Optional<UserPreferences> userPreferences = this.userPreferencesRepository
                .findUserPreferencesByUserId(savedUser.getId());

        assertThat(userPreferences)
                .isPresent()
                .satisfies(content -> {
                    assertThat(content.get().getLanguage()).isEqualTo("PT-BR");
                    assertThat(content.get().getWhatsAppPromotional()).isEqualTo(Boolean.FALSE);
                    assertThat(content.get().getUserPreferencesId()).isNotNull();
                });
    }

}