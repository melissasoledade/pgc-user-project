package com.user.application.models.event;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserDataEvent {

    private Long userId;
    private String name;
    private String email;
    private String phoneNumber;
    private String cpf;
    private String gender;
    private LocalDate birthDate;
    private String origin;
    private UserAddressEvent address;
    private UserProfilesEvent userProfiles;
    private UserPreferencesEvent userPreferences;
}
