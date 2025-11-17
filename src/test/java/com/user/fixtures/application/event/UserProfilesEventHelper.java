package com.user.fixtures.application.event;

import com.user.application.models.event.UserProfilesEvent;

public class UserProfilesEventHelper {

    public static UserProfilesEvent.UserProfilesEventBuilder defaultUserProfilesEvent() {
        return UserProfilesEvent.builder()
                .profileCode(2L)
                .profileName("tenant");
    }
}
