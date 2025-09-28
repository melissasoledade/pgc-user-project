package com.user.application.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfilesDTO {

    private Long profileCode;
    private String profileName;
}
