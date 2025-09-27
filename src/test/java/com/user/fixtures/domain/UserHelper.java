package com.user.fixtures.domain;

import com.user.domain.entities.User;

import java.text.ParseException;

import static com.user.fixtures.common.DateHelper.parseDateFromString;

public class UserHelper {

    public static User.UserBuilder defaultUser() throws ParseException {
        return User.builder()
                .id(4L)
                .name("Ana da Silva")
                .phoneNumber("+5511988888888")
                .email("ana@gmail.com")
                .cpf("99999999999")
                .gender("feminine")
                .birthDate(parseDateFromString("yyyy-MM-dd","1999-01-01"));
    }
}
