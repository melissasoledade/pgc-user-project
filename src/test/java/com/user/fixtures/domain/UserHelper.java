package com.user.fixtures.domain;

import com.user.domain.entities.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UserHelper {

    public static User.UserBuilder defaultUser() throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return User.builder()
                .id(4L)
                .name("Ana da Silva")
                .phoneNumber("+5511988888888")
                .email("ana@gmail.com")
                .cpf("99999999999")
                .gender("feminine")
                .birthDate(formatter.parse("1999-01-01"))
                .address(AddressHelper.defaultAddress().build())
                .userProfiles(UserProfilesHelper.defaultUserProfiles().build())
                .userPreferences(UserPreferencesHelper.defaultUserPreferences().build());
    }
}
