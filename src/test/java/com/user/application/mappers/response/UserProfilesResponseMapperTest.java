package com.user.application.mappers.response;

import com.user.application.dto.response.UserProfilesResponseDTO;
import com.user.domain.entities.UserProfiles;
import com.user.fixtures.application.response.UserProfilesResponseDTOHelper;
import com.user.fixtures.domain.UserProfilesHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserProfilesResponseMapperTest {

    @Autowired
    private UserProfilesResponseMapper mapper;

    @Test
    void shouldMapUserProfilesEntityToUserProfilesResponseDTO() {
        // given
        final UserProfilesResponseDTO userProfilesResponseDTO = UserProfilesResponseDTOHelper
                .defaultUserProfilesResponseDTO().build();
        final UserProfiles userProfiles = UserProfilesHelper.defaultUserProfiles().build();

        // when
        final UserProfilesResponseDTO result = this.mapper.fromUserProfiles(userProfiles);

        // then
        assertEquals(userProfilesResponseDTO.getProfileCode(), result.getProfileCode());
        assertEquals(userProfilesResponseDTO.getProfileName(), result.getProfileName());
    }

}