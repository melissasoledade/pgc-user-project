package com.user.application.mappers.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gravity9.jsonpatch.JsonPatch;
import com.gravity9.jsonpatch.Patch;
import com.gravity9.jsonpatch.ReplaceOperation;
import com.user.application.models.EventType;
import com.user.application.models.event.UserDataEvent;
import com.user.application.models.event.UserEvent;
import com.user.domain.entities.User;
import com.user.fixtures.application.event.UserEventHelper;
import com.user.fixtures.domain.AddressHelper;
import com.user.fixtures.domain.UserHelper;
import com.user.fixtures.domain.UserPreferencesHelper;
import com.user.fixtures.domain.UserProfilesHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserEventMapperTest {

    @Autowired
    private UserDataEventMapper userDataEventMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserEventMapper mapper;

    @Test
    void shouldMapEntityToEvent() throws ParseException {
        // given
        final User user = UserHelper.defaultUser()
                .id(4L)
                .userProfiles(UserProfilesHelper.defaultUserProfiles().build())
                .userPreferences(UserPreferencesHelper.defaultUserPreferences().build())
                .address(AddressHelper.defaultAddress().build())
                .build();
        final UserEvent event = UserEventHelper.defaultUserEvent().build();

        // when
        final UserEvent result = this.mapper.fromUser(user, EventType.CREATION);

        // then
        assertEquals(event.getEventData().getUserId(), result.getEventData().getUserId());
        assertEquals(event.getEventData().getCpf(), result.getEventData().getCpf());
        assertEquals(event.getEventData().getAddress().getAddressName(),
                result.getEventData().getAddress().getAddressName());
    }

    @Test
    void shouldMapPatchToEventSuccessfully() throws Exception {
        // given
        final User user = UserHelper.defaultUser()
                .id(1L)
                .build();

        final Patch patch = new JsonPatch(
                java.util.List.of(
                        new ReplaceOperation(
                                "/userPreferences/emailOptIn",
                                objectMapper.valueToTree(true)),
                        new ReplaceOperation(
                                "/cpf",
                                objectMapper.valueToTree("55555555555"))));

        // when
        final UserEvent result = mapper.fromPatch(user, patch, EventType.PARTIAL_UPDATE);

        // then
        final UserDataEvent eventData = result.getEventData();

        assertEquals(EventType.PARTIAL_UPDATE, result.getEventType());
        assertEquals(user.getId(), eventData.getUserId());
        assertEquals("55555555555", eventData.getCpf());
        assertEquals(true, eventData.getUserPreferences().getEmailOptIn());
    }



}