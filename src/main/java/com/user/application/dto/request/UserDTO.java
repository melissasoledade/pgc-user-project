package com.user.application.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserDTO {

    private String name;
    private String email;
    private String phoneNumber;
    private String cpf;
    private String gender;
    private Date birthDate;
    private AddressDTO address;
    private UserProfilesDTO userProfiles;
    private UserPreferencesDTO userPreferences;
}
