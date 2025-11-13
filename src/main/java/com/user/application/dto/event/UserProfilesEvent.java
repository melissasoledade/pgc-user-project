package com.user.application.dto.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfilesEvent {

    private Long profileCode;
    private String profileName;
}
