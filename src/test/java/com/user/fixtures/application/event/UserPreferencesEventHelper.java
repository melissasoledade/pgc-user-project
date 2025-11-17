package com.user.fixtures.application.event;

import com.user.application.models.event.UserPreferencesEvent;

import java.util.TimeZone;

public class UserPreferencesEventHelper {

    public static UserPreferencesEvent.UserPreferencesEventBuilder defaultUserPreferencesEvent() {
        return UserPreferencesEvent.builder()
                .whatsAppOptIn(Boolean.TRUE)
                .whatsAppPromotional(Boolean.FALSE)
                .emailOptIn(Boolean.FALSE)
                .emailPromotional(Boolean.TRUE)
                .phoneOptIn(Boolean.TRUE)
                .pushNotification(Boolean.TRUE)
                .language("PT-BR")
                .timeZone(TimeZone.getTimeZone("BET"));
    }
}
