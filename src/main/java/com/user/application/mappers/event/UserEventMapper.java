package com.user.application.mappers.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gravity9.jsonpatch.JsonPatchException;
import com.gravity9.jsonpatch.Patch;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserEventMapper {

    private final UserDataEventMapper userDataEventMapper;
    private final ObjectMapper objectMapper;

    public UserEvent fromUser(User user, EventType eventType) {
        final UserDataEvent eventData = userDataEventMapper.fromUser(user);

        return UserEvent.builder()
                .eventType(eventType)
                .eventData(eventData)
                .timestamp(LocalDateTime.now()
                                .atZone(ZoneId.systemDefault())
                                .toInstant()
                                .toEpochMilli())
                .referenceDate(LocalDateTime.now())
                .build();
    }

    public UserEvent fromPatch(User user, Patch patch, EventType eventType)
            throws JsonPatchException, JsonProcessingException {

        final UserDataEvent baseEventData = UserDataEvent.builder()
                .userId(user.getId())
                .build();

        final JsonNode baseNode = objectMapper.valueToTree(baseEventData);
        final JsonNode patchedNode = patch.apply(baseNode);

        final UserDataEvent patchedEventData =
                objectMapper.treeToValue(patchedNode, UserDataEvent.class);

        return UserEvent.builder()
                .eventType(eventType)
                .eventData(patchedEventData)
                .timestamp(LocalDateTime.now()
                                .atZone(ZoneId.systemDefault())
                                .toInstant()
                                .toEpochMilli())
                .referenceDate(LocalDateTime.now())
                .build();
    }
}
