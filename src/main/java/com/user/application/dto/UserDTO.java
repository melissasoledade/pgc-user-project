package com.user.application.dto;

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
    private AddressDTO addressDTO;
    private UserProfilesDTO profilesDTO;
    private UserPreferencesDTO preferencesDTO;
}
