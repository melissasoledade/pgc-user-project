package com.user.application.models.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserResponseDTO {

    private Long userId;
    private String name;
    private String email;
    private String phoneNumber;
    private String cpf;
    private String gender;
    private LocalDate birthDate;
    private String origin;
    private AddressResponseDTO address;
    private UserProfilesResponseDTO userProfiles;
    private UserPreferencesResponseDTO userPreferences;
}
