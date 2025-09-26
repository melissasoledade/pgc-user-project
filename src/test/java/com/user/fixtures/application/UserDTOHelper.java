package com.user.fixtures.application;

import com.user.application.dto.UserDTO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UserDTOHelper {

    public static UserDTO.UserDTOBuilder defaultUserDTO() throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return UserDTO.builder()
                .name("Ana da Silva")
                .phoneNumber("+5511988888888")
                .email("ana@gmail.com")
                .cpf("99999999999")
                .gender("feminine")
                .birthDate(formatter.parse("1999-01-01"))
                .addressDTO(AddressDTOHelper.defaultAddressDTO().build())
                .profilesDTO(UserProfilesDTOHelper.defaultUserProfilesDTO().build());
    }
}
