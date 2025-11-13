package com.user.fixtures.application.event;

import com.user.application.dto.event.UserEvent;

import java.time.LocalDateTime;

public class UserEventHelper {

    public static UserEvent.UserEventBuilder defaultUserEvent() {
        return UserEvent.builder()
                .referenceDate(LocalDateTime.now());
    }
}
