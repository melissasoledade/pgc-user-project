package com.user.infrastructure.repositories;

import com.user.domain.entities.User;
import com.user.domain.entities.UserProfiles;
import com.user.fixtures.domain.AddressHelper;
import com.user.fixtures.domain.UserHelper;
import com.user.fixtures.domain.UserPreferencesHelper;
import com.user.fixtures.domain.UserProfilesHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserJpaRepositoryIT {

    @Autowired
    private UserJpaRepository repository;

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
        assertEquals(1L, result.getId());
    }

}