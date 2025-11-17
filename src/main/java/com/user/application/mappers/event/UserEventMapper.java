package com.user.application.mappers.event;

import com.user.application.models.EventType;
import com.user.application.models.event.UserDataEvent;
import com.user.application.models.event.UserEvent;
import com.user.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Builder
@AllArgsConstructor
@Component
public class UserEventMapper {

    private final UserDataEventMapper userDataEventMapper;

    public UserEvent fromUser(User user, EventType eventType) {
        final UserDataEvent eventData = userDataEventMapper.fromUser(user);
        return UserEvent.builder()
                .eventType(eventType)
                .eventData(eventData)
                .timestamp(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .referenceDate(LocalDateTime.now())
                .build();
    }
}
