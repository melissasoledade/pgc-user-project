package com.user.application.mappers.response;

import com.user.application.dto.response.UserResponseDTO;
import com.user.domain.entities.User;
import com.user.fixtures.application.response.UserResponseDTOHelper;
import com.user.fixtures.domain.UserHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserResponseMapperTest {

    @Autowired
    private UserResponseMapper userResponseMapper;

    @Autowired
    private UserProfilesResponseMapper userProfilesResponseMapper;

    @Autowired
    private UserPreferencesResponseMapper userPreferencesResponseMapper;

    @Autowired
    private AddressResponseMapper addressResponseMapper;

    @Test
    void shouldMapUserEntityToUserResponseDTO() throws ParseException {
        // given
        final User user = UserHelper.defaultUser().build();
        final UserResponseDTO userResponseDTO = UserResponseDTOHelper
                .defaultUserResponseDTO().build();

        // when
        final UserResponseDTO result = this.userResponseMapper.fromUser(user);

        assertEquals(4L, result.getUserId());
        assertEquals("", result.getUserProfiles().getProfileName());
    }

}