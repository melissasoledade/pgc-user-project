package com.user.application.dto.event;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserEvent {

    private String eventName;
    private Long timestamp;
    private LocalDateTime referenceDate;
    private String origin;
    private UserDataEvent eventData;
}
