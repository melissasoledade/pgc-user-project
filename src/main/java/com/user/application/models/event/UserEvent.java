package com.user.application.models.event;

import com.user.application.models.EventType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserEvent {

    private EventType eventType;
    private Long timestamp;
    private LocalDateTime referenceDate;
    private String origin;
    private UserDataEvent eventData;
}
