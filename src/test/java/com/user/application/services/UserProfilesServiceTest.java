package com.user.application.services;

import com.user.application.dto.response.UserProfilesResponseDTO;
import com.user.application.exceptionhandler.exceptions.UserNotFoundException;
import com.user.application.mappers.response.UserProfilesResponseMapper;
import com.user.domain.entities.UserProfiles;
import com.user.domain.repositories.BaseUserProfilesRepository;
import com.user.fixtures.application.response.UserProfilesResponseDTOHelper;
import com.user.fixtures.domain.UserProfilesHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserProfilesServiceTest {

    @Mock
    private BaseUserProfilesRepository repository;

    @Mock
    private UserProfilesResponseMapper mapper;

    @InjectMocks
    private UserProfilesService service;

    @Test
    void shouldGetUserProfilesByUserId() {
        // given
        final UserProfiles userProfiles = UserProfilesHelper.defaultUserProfiles().build();
        final UserProfilesResponseDTO userProfilesResponseDTO = UserProfilesResponseDTOHelper
                .defaultUserProfilesResponseDTO().build();

        when(this.repository.findUserProfilesByUserId(2L)).thenReturn(Optional.ofNullable(userProfiles));
        when(this.mapper.fromUserProfiles(userProfiles)).thenReturn(userProfilesResponseDTO);

        // when
        final UserProfilesResponseDTO result = this.service.getUserProfiles(2L);

        // then
        assertEquals("tenant", result.getProfileName());
        assertEquals(2L, result.getProfileCode());
    }

    @Test
    void shouldThrowUserNotFoundExceptionWhenUserIdIsNotFound() {
        // given
        when(this.repository.findUserProfilesByUserId(2L)).thenReturn(Optional.empty());

        // when & then
        assertThrows(UserNotFoundException.class, () -> this.service.getUserProfiles(2L));
    }

}