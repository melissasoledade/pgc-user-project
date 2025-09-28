package com.user.fixtures.application.request;

import com.user.application.dto.request.UserProfilesDTO;

public class UserProfilesDTOHelper {

    public static UserProfilesDTO.UserProfilesDTOBuilder defaultUserProfilesDTO() {
        return UserProfilesDTO.builder()
                .profileCode(2L)
                .profileName("tenant");
    }
}
