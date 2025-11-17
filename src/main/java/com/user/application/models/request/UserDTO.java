package com.user.application.models.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserDTO {

    private String name;
    private String email;
    private String phoneNumber;
    private String cpf;
    private String gender;
    private LocalDate birthDate;
    private String origin;
    private AddressDTO address;
    private UserProfilesDTO userProfiles;
    private UserPreferencesDTO userPreferences;
}
