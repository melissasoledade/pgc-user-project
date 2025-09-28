package com.user.fixtures.application.response;

import com.user.application.dto.response.UserResponseDTO;

import java.text.ParseException;

import static com.user.fixtures.common.DateHelper.parseDateFromString;

public class UserResponseDTOHelper {

    public static UserResponseDTO.UserResponseDTOBuilder defaultUserResponseDTO() throws ParseException {
        return UserResponseDTO.builder()
                .userId(4L)
                .name("Ana da Silva")
                .phoneNumber("+5511988888888")
                .email("ana@gmail.com")
                .cpf("99999999999")
                .gender("feminine")
                .birthDate(parseDateFromString("yyyy-MM-dd","1999-01-01"))
                .userProfiles(UserProfilesResponseDTOHelper.defaultUserProfilesResponseDTO().build())
                .userPreferences(UserPreferencesResponseDTOHelper.defaultUserPreferencesResponseDTO().build())
                .address(AddressResponseDTOHelper.defaultAddressResponseDTO().build());

    }
}
