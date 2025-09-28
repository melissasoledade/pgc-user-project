package com.user.fixtures.application.request;

import com.user.application.dto.request.UserPreferencesDTO;

import java.util.TimeZone;

public class UserPreferencesDTOHelper {

    public static UserPreferencesDTO.UserPreferencesDTOBuilder defaultUserPreferencesDTO() {
        return UserPreferencesDTO.builder()
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
