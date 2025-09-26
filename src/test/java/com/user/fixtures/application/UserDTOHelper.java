package com.user.fixtures.application;

import com.user.application.dto.UserDTO;

import java.text.ParseException;

import static com.user.fixtures.common.DateHelper.parseDateFromString;

public class UserDTOHelper {

    public static UserDTO.UserDTOBuilder defaultUserDTO() throws ParseException {
        return UserDTO.builder()
                .name("Ana da Silva")
                .phoneNumber("+5511988888888")
                .email("ana@gmail.com")
                .cpf("99999999999")
                .gender("feminine")
                .birthDate(parseDateFromString("yyyy-MM-dd","1999-01-01"))
                .addressDTO(AddressDTOHelper.defaultAddressDTO().build())
                .profilesDTO(UserProfilesDTOHelper.defaultUserProfilesDTO().build())
                .preferencesDTO(UserPreferencesDTOHelper.defaultUserPreferencesDTO().build());
    }
}
