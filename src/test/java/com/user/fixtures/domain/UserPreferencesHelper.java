package com.user.fixtures.domain;

import com.user.domain.entities.UserPreferences;

import java.util.TimeZone;

public class UserPreferencesHelper {

    public static UserPreferences.UserPreferencesBuilder defaultUserPreferences() {
        return UserPreferences.builder()
                .userPreferencesId(3L)
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
