package com.user.fixtures.application.response;

import com.user.application.dto.response.UserProfilesResponseDTO;

public class UserProfilesResponseDTOHelper {

    public static UserProfilesResponseDTO.UserProfilesResponseDTOBuilder
    defaultUserProfilesResponseDTO() {
        return UserProfilesResponseDTO.builder()
                .profileCode(2L)
                .profileName("tenant");
    }
}
