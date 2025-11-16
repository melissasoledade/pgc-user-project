package com.user.application.dto.event;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserDataEvent {

    private Long userId;
    private String name;
    private String email;
    private String phoneNumber;
    private String cpf;
    private String gender;
    private Date birthDate;
    private String origin;
    private UserAddressEvent address;
    private UserProfilesEvent userProfiles;
    private UserPreferencesEvent userPreferences;
}
