package com.user.fixtures.domain;

import com.user.domain.entities.UserProfiles;

public class UserProfilesHelper {

    public static UserProfiles.UserProfilesBuilder defaultUserProfiles() {
        return UserProfiles.builder()
                .userProfilesId(2L)
                .profileCode(2L)
                .profileName("tenant");
    }
}
