package com.user.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfilesDTO {

    private String profileCode;
    private String profileName;
}
