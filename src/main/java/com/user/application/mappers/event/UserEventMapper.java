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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Builder
@AllArgsConstructor
@Component
@Slf4j
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

        ObjectMapper nonNullMapper = objectMapper.copy();
        nonNullMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        final UserDataEvent baseEventData = UserDataEvent.builder()
                .userId(user.getId())
                .build();

        JsonNode baseNode = objectMapper.valueToTree(baseEventData);
        JsonNode patchedNode = patch.apply(baseNode);

        // JsonNode -> POJO (contains nulls)
        UserDataEvent temp =
                objectMapper.treeToValue(patchedNode, UserDataEvent.class);

        String jsonWithoutNulls =
                nonNullMapper.writeValueAsString(temp);

        // Deserialize clean object
        UserDataEvent patchedEventData =
                objectMapper.readValue(jsonWithoutNulls, UserDataEvent.class);

        log.info("json without null {}, eventData {}", jsonWithoutNulls, patchedEventData);

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
