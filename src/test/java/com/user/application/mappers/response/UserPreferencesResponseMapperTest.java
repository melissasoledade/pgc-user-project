package com.user.application.mappers.response;

import com.user.application.dto.response.UserPreferencesResponseDTO;
import com.user.domain.entities.UserPreferences;
import com.user.fixtures.application.response.UserPreferencesResponseDTOHelper;
import com.user.fixtures.domain.UserPreferencesHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserPreferencesResponseMapperTest {

    @Autowired
    private UserPreferencesResponseMapper mapper;

    @Test
    void shouldMapUserPreferencesEntityToUserPreferencesDTO() {
        // given
        final UserPreferences userPreferences = UserPreferencesHelper
                .defaultUserPreferences().build();
        final UserPreferencesResponseDTO userPreferencesResponseDTO = UserPreferencesResponseDTOHelper
                .defaultUserPreferencesResponseDTO().build();

        // when
        final UserPreferencesResponseDTO result = this.mapper.fromUserPreferences(userPreferences);

        // then
        assertEquals(userPreferencesResponseDTO.getTimeZone(), result.getTimeZone());
        assertEquals(userPreferencesResponseDTO.getLanguage(), result.getLanguage());
        assertEquals(userPreferencesResponseDTO.getEmailOptIn(), result.getEmailOptIn());
        assertEquals(userPreferencesResponseDTO.getEmailPromotional(), result.getEmailPromotional());
        assertEquals(userPreferencesResponseDTO.getPushNotification(), result.getPushNotification());
        assertEquals(userPreferencesResponseDTO.getWhatsAppOptIn(), result.getWhatsAppOptIn());
    }

}