package com.user.application.mappers;

import com.user.application.dto.request.UserProfilesDTO;
import com.user.domain.entities.UserProfiles;
import com.user.fixtures.application.UserProfilesDTOHelper;
import com.user.fixtures.domain.UserProfilesHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserProfilesMapperTest {

    @Autowired
    private UserProfilesMapper mapper;

    @Test
    void shouldMapUserProfilesDTOToUserProfilesEntity() {
        // given
        final UserProfiles userProfiles = UserProfilesHelper
                .defaultUserProfiles().build();
        final UserProfilesDTO userProfilesDTO = UserProfilesDTOHelper
                .defaultUserProfilesDTO().build();

        // when
        final UserProfiles result = mapper.toUserProfiles(userProfilesDTO);

        // then
        assertEquals(userProfiles.getProfileName(), result.getProfileName());
        assertEquals(userProfiles.getProfileCode(), result.getProfileCode());
        assertNull(result.getUserProfilesId());
    }

    @Test
    void shouldMapUserProfilesEntityToUserProfilesDTO() {
        // given
        final UserProfiles userProfiles = UserProfilesHelper
                .defaultUserProfiles().build();
        final UserProfilesDTO userProfilesDTO = UserProfilesDTOHelper
                .defaultUserProfilesDTO().build();

        // when
        final UserProfilesDTO result = mapper.fromUserProfiles(userProfiles);

        // then
        assertEquals(userProfilesDTO.getProfileName(), result.getProfileName());
        assertEquals(userProfilesDTO.getProfileCode(), result.getProfileCode());
    }

}