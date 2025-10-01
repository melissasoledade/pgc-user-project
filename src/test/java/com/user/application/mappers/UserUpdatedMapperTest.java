package com.user.application.mappers;

import com.user.application.dto.request.UserDTO;
import com.user.domain.entities.User;
import com.user.fixtures.application.request.AddressDTOHelper;
import com.user.fixtures.application.request.UserDTOHelper;
import com.user.fixtures.application.request.UserPreferencesDTOHelper;
import com.user.fixtures.application.request.UserProfilesDTOHelper;
import com.user.fixtures.domain.AddressHelper;
import com.user.fixtures.domain.UserHelper;
import com.user.fixtures.domain.UserPreferencesHelper;
import com.user.fixtures.domain.UserProfilesHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserUpdatedMapperTest {

    @Autowired
    private UserUpdatedMapper mapper;

    @Test
    void shouldUpdateUserWithUserDTO() throws ParseException {
        // given
        final User user = UserHelper.defaultUser()
                .createdAt(LocalDateTime.of(2025, 9, 1, 10, 10))
                .updatedAt(LocalDateTime.of(2025 , 9, 5, 8, 15))
                .userProfiles(UserProfilesHelper.defaultUserProfiles().build())
                .userPreferences(UserPreferencesHelper.defaultUserPreferences().build())
                .address(AddressHelper.defaultAddress().build())
                .build();
        final UserDTO userDTO = UserDTOHelper.defaultUserDTO()
                .email("ana92@gmail.com")
                .address(AddressDTOHelper.defaultAddressDTO()
                        .neighbourhood("Campestre")
                        .build())
                .userPreferences(UserPreferencesDTOHelper.defaultUserPreferencesDTO()
                        .phoneOptIn(Boolean.FALSE)
                        .build())
                .userProfiles(UserProfilesDTOHelper.defaultUserProfilesDTO()
                        .profileCode(3L)
                        .profileName("agent")
                        .build())
                .build();

        // when
        this.mapper.updateUserFromDTO(userDTO, user);

        // then
        assertEquals(4L, user.getId());
        assertEquals("ana92@gmail.com", user.getEmail());
        assertEquals("Campestre", user.getAddress().getNeighbourhood());
        assertEquals(Boolean.FALSE, user.getUserPreferences().getPhoneOptIn());
        assertEquals(3L, user.getUserProfiles().getProfileCode());
        assertEquals("agent", user.getUserProfiles().getProfileName());
        assertEquals(3L, user.getUserPreferences().getUserPreferencesId());
        assertEquals(LocalDateTime.parse("2025-09-01T10:10"), user.getCreatedAt());
        assertEquals(LocalDateTime.parse("2025-09-05T08:15"), user.getUpdatedAt());
    }

    @Test
    void shouldNotMapNullValuesToUser() throws ParseException {
        // given
        final User user = UserHelper.defaultUser()
                .createdAt(LocalDateTime.of(2025, 9, 1, 10, 10))
                .updatedAt(LocalDateTime.of(2025 , 9, 5, 8, 15))
                .userProfiles(UserProfilesHelper.defaultUserProfiles().build())
                .userPreferences(UserPreferencesHelper.defaultUserPreferences().build())
                .address(AddressHelper.defaultAddress().build())
                .build();

        final UserDTO userDTO = UserDTOHelper.defaultUserDTO()
                .name(null)
                .cpf(null)
                .build();

        // when
        this.mapper.updateUserFromDTO(userDTO, user);

        // then
        assertEquals("Ana da Silva", user.getName());
        assertEquals("99999999999", user.getCpf());
    }

}