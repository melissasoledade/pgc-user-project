package com.user.fixtures.application;

import com.user.application.dto.UserProfilesDTO;

public class UserProfilesDTOHelper {

    public static UserProfilesDTO.UserProfilesDTOBuilder defaultUserProfilesDTO() {
        return UserProfilesDTO.builder()
                .profileCode(2L)
                .profileName("tenant");
    }
}
