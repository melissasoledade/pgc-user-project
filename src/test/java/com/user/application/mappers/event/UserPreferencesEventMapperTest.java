package com.user.application.mappers.event;

import com.user.application.models.event.UserPreferencesEvent;
import com.user.domain.entities.UserPreferences;
import com.user.fixtures.application.event.UserPreferencesEventHelper;
import com.user.fixtures.domain.UserPreferencesHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserPreferencesEventMapperTest {

    @Autowired
    private UserPreferencesEventMapper mapper;

    @Test
    void shouldMapEntityToEvent() {
        // given
        final UserPreferences userPreferences = UserPreferencesHelper.defaultUserPreferences().build();
        final UserPreferencesEvent event = UserPreferencesEventHelper.defaultUserPreferencesEvent().build();

        // when
        final UserPreferencesEvent result = this.mapper.fromUserPreferences(userPreferences);

        // then
        assertEquals(event.getTimeZone(), result.getTimeZone());
        assertEquals(event.getLanguage(), result.getLanguage());
        assertEquals(event.getEmailOptIn(), result.getEmailOptIn());
        assertEquals(event.getEmailPromotional(), result.getEmailPromotional());
        assertEquals(event.getPushNotification(), result.getPushNotification());
        assertEquals(event.getWhatsAppOptIn(), result.getWhatsAppOptIn());
    }

}