package com.user.application.mappers.event;

import com.user.application.dto.event.UserEvent;
import com.user.domain.entities.User;
import com.user.fixtures.application.event.UserEventHelper;
import com.user.fixtures.domain.AddressHelper;
import com.user.fixtures.domain.UserHelper;
import com.user.fixtures.domain.UserPreferencesHelper;
import com.user.fixtures.domain.UserProfilesHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserEventMapperTest {

    @Mock
    private UserDataEventMapper userDataEventMapper;

    @InjectMocks
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

        when(userDataEventMapper.fromUser(user)).thenReturn(event.getEventData());

        // when
        final UserEvent result = this.mapper.fromUser(user);

        // then
        assertEquals(event.getEventData().getUserId(), result.getEventData().getUserId());
        assertEquals(event.getEventData().getCpf(), result.getEventData().getCpf());
        assertEquals(event.getEventData().getAddress().getAddressName(),
                result.getEventData().getAddress().getAddressName());
    }



}