package com.user.application.mappers.event;

import com.user.application.dto.event.UserDataEvent;
import com.user.application.dto.event.UserEvent;
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

    public UserEvent fromUser(User user) {
        final UserDataEvent eventData= userDataEventMapper.fromUser(user);
        return UserEvent.builder()
                .eventData(eventData)
                .timestamp(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                .referenceDate(LocalDateTime.now())
                .build();
    }
}
