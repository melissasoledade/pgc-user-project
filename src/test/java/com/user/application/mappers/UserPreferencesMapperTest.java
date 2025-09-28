package com.user.application.mappers;

import com.user.application.dto.request.UserPreferencesDTO;
import com.user.domain.entities.UserPreferences;
import com.user.fixtures.application.UserPreferencesDTOHelper;
import com.user.fixtures.domain.UserPreferencesHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserPreferencesMapperTest {

    @Autowired
    private UserPreferencesMapper mapper;

    @Test
    void shouldMapUserPreferencesDTOToUserPreferencesEntity() {
        // given
        final UserPreferences userPreferences = UserPreferencesHelper
                .defaultUserPreferences().build();
        final UserPreferencesDTO userPreferencesDTO = UserPreferencesDTOHelper
                .defaultUserPreferencesDTO().build();

        // when
        final UserPreferences result = mapper.toUserPreferences(userPreferencesDTO);

        // then
        assertEquals(userPreferences.getLanguage(), result.getLanguage());
        assertEquals(userPreferences.getTimeZone(), result.getTimeZone());
        assertNull(result.getUserPreferencesId());
        assertNull(result.getCreatedAt());
        assertNull(result.getUpdatedAt());
        assertEquals(userPreferences.getEmailOptIn(), result.getEmailOptIn());
        assertEquals(userPreferences.getEmailPromotional(), result.getEmailPromotional());
        assertEquals(userPreferences.getPushNotification(), result.getPushNotification());
        assertEquals(userPreferences.getPhoneOptIn(), result.getPhoneOptIn());
        assertEquals(userPreferences.getWhatsAppOptIn(), result.getWhatsAppOptIn());
        assertEquals(userPreferences.getWhatsAppPromotional(), result.getWhatsAppPromotional());
    }

    @Test
    void shouldMapUserPreferencesEntityToUserPreferencesDTO() {
        // given
        final UserPreferences userPreferences = UserPreferencesHelper
                .defaultUserPreferences().build();
        final UserPreferencesDTO userPreferencesDTO = UserPreferencesDTOHelper
                .defaultUserPreferencesDTO().build();

        // when
        final UserPreferencesDTO result = mapper.fromUserPreferences(userPreferences);

        // then
        assertEquals(userPreferencesDTO.getLanguage(), result.getLanguage());
        assertEquals(userPreferencesDTO.getTimeZone(), result.getTimeZone());
        assertEquals(userPreferencesDTO.getEmailOptIn(), result.getEmailOptIn());
        assertEquals(userPreferencesDTO.getEmailPromotional(), result.getEmailPromotional());
        assertEquals(userPreferencesDTO.getPushNotification(), result.getPushNotification());
        assertEquals(userPreferencesDTO.getPhoneOptIn(), result.getPhoneOptIn());
        assertEquals(userPreferencesDTO.getWhatsAppOptIn(), result.getWhatsAppOptIn());
        assertEquals(userPreferencesDTO.getWhatsAppPromotional(), result.getWhatsAppPromotional());
    }

}