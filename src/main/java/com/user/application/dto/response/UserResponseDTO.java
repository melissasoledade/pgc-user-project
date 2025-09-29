package com.user.application.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserResponseDTO {

    private Long userId;
    private String name;
    private String email;
    private String phoneNumber;
    private String cpf;
    private String gender;
    private Date birthDate;
    private AddressResponseDTO address;
    private UserProfilesResponseDTO userProfiles;
    private UserPreferencesResponseDTO userPreferences;
}
