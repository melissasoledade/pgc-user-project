package com.user.fixtures.application.event;

import com.user.application.models.event.UserDataEvent;

import java.text.ParseException;

import static com.user.fixtures.common.DateHelper.parseDateFromString;

public class UserDataEventHelper {

    public static UserDataEvent.UserDataEventBuilder defaultUserDataEvent() throws ParseException {
        return UserDataEvent.builder()
                .userId(4L)
                .name("Ana da Silva")
                .phoneNumber("+5511988888888")
                .email("ana@gmail.com")
                .cpf("99999999999")
                .gender("feminine")
                .birthDate(parseDateFromString("yyyy-MM-dd","1999-01-01"))
                .userProfiles(UserProfilesEventHelper.defaultUserProfilesEvent().build())
                .userPreferences(UserPreferencesEventHelper.defaultUserPreferencesEvent().build())
                .address(UserAddressEventHelper.defaultUserAddressEvent().build());
    }
}
