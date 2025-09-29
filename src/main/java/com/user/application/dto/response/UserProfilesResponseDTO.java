package com.user.application.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfilesResponseDTO {

    private Long profileCode;
    private String profileName;
}
