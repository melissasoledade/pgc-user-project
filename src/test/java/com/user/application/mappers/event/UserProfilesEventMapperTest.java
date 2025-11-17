package com.user.application.mappers.event;

import com.user.application.models.event.UserProfilesEvent;
import com.user.domain.entities.UserProfiles;
import com.user.fixtures.application.event.UserProfilesEventHelper;
import com.user.fixtures.domain.UserProfilesHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserProfilesEventMapperTest {

    @Autowired
    private UserProfilesEventMapper mapper;

    @Test
    void shouldMapEntityToEvent() {
        // given
        final UserProfiles userProfiles = UserProfilesHelper.defaultUserProfiles().build();
        final UserProfilesEvent event = UserProfilesEventHelper.defaultUserProfilesEvent().build();

        // when
        final UserProfilesEvent result = this.mapper.fromUserProfiles(userProfiles);

        // then
        assertEquals(event.getProfileCode(), result.getProfileCode());
        assertEquals(event.getProfileName(), result.getProfileName());
    }

}