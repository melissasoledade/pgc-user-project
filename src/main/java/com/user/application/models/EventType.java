package com.user.application.models;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum EventType {

    CREATION("creation", "USER_CREATION"),
    UPDATE("update", "USER_UPDATE"),
    PARTIAL_UPDATE("partial_update", "USER_PARTIAL_UPDATE"),
    DELETION("deletion", "USER_DELETION");

    private final String type;
    private final String eventName;

    public String getType() {
        return type;
    }

    public String getEventName() {
        return eventName;
    }

    public static EventType from(String type) {
        return Arrays.stream(values())
                .filter(eventType -> eventType.type.equalsIgnoreCase(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid event type: " + type));
    }
}
