package com.user.fixtures.application.response;

import com.user.application.dto.response.UserPreferencesResponseDTO;

import java.util.TimeZone;

public class UserPreferencesResponseDTOHelper {

    public static UserPreferencesResponseDTO.UserPreferencesResponseDTOBuilder
    defaultUserPreferencesResponseDTO() {
        return UserPreferencesResponseDTO.builder()
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
