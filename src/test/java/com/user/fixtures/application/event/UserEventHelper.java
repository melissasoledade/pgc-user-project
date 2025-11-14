package com.user.fixtures.application.event;

import com.user.application.dto.EventType;
import com.user.application.dto.event.UserEvent;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class UserEventHelper {

    public static UserEvent.UserEventBuilder defaultUserEvent() throws ParseException {
        return UserEvent.builder()
                .eventData(UserDataEventHelper.defaultUserDataEvent().build())
                .eventType(EventType.CREATION)
                .timestamp(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .referenceDate(LocalDateTime.now());
    }
}
