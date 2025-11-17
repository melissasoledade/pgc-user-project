package com.user.application.mappers.event;

import com.user.application.models.event.UserDataEvent;
import com.user.domain.entities.User;
import com.user.fixtures.application.event.UserDataEventHelper;
import com.user.fixtures.domain.AddressHelper;
import com.user.fixtures.domain.UserHelper;
import com.user.fixtures.domain.UserPreferencesHelper;
import com.user.fixtures.domain.UserProfilesHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserDataEventMapperTest {

    @Autowired
    private UserDataEventMapper userDataEventMapper;

    @Autowired
    private UserAddressEventMapper addressEventMapper;

    @Autowired
    private UserPreferencesEventMapper preferencesEventMapper;

    @Autowired
    private UserProfilesEventMapper profilesEventMapper;

    @Test
    void shouldMapEntityToEvent() throws ParseException {
        // given
        final User user = UserHelper.defaultUser()
                .userProfiles(UserProfilesHelper.defaultUserProfiles().build())
                .userPreferences(UserPreferencesHelper.defaultUserPreferences().build())
                .address(AddressHelper.defaultAddress().build())
                .build();
        final UserDataEvent event = UserDataEventHelper.defaultUserDataEvent().build();

        // when
        final UserDataEvent result = this.userDataEventMapper.fromUser(user);

        // then
        assertEquals(event.getUserId(), result.getUserId());
        assertEquals(event.getUserProfiles().getProfileName(),
                result.getUserProfiles().getProfileName());
        assertEquals(event.getUserPreferences().getWhatsAppOptIn(),
                result.getUserPreferences().getWhatsAppOptIn());
        assertEquals(event.getBirthDate(), result.getBirthDate());
        assertEquals(event.getCpf(),
                result.getCpf());
        assertEquals(event.getAddress().getAddressName(),
                    result.getAddress().getAddressName());
    }

}