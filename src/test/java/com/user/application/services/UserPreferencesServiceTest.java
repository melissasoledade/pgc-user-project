package com.user.application.services;

import com.user.application.dto.response.UserPreferencesResponseDTO;
import com.user.application.exceptionhandler.exceptions.UserNotFoundException;
import com.user.application.mappers.response.UserPreferencesResponseMapper;
import com.user.domain.entities.UserPreferences;
import com.user.domain.repositories.BaseUserPreferencesRepository;
import com.user.fixtures.application.response.UserPreferencesResponseDTOHelper;
import com.user.fixtures.domain.UserPreferencesHelper;
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
class UserPreferencesServiceTest {

    @Mock
    private BaseUserPreferencesRepository repository;

    @Mock
    private UserPreferencesResponseMapper mapper;

    @InjectMocks
    private UserPreferencesService service;

    @Test
    void shouldGetUserPreferencesByUserId() {
        // given
        final UserPreferences userPreferences = UserPreferencesHelper.defaultUserPreferences().build();
        final UserPreferencesResponseDTO userPreferencesResponseDTO =
                UserPreferencesResponseDTOHelper.defaultUserPreferencesResponseDTO().build();

        when(this.repository.findUserPreferencesByUserId(3L)).thenReturn(Optional.ofNullable(userPreferences));
        when(this.mapper.fromUserPreferences(userPreferences)).thenReturn(userPreferencesResponseDTO);

        // when
        final UserPreferencesResponseDTO result = this.service.getUserPreferences(3L);

        // then
        assertEquals(Boolean.TRUE, result.getWhatsAppOptIn());
        assertEquals(Boolean.FALSE, result.getWhatsAppPromotional());
        assertEquals(Boolean.FALSE, result.getEmailOptIn());
        assertEquals("PT-BR", result.getLanguage());
    }

    @Test
    void shouldThrowUserNotFoundExceptionWhenUserIdIsNotFound() {
        // given
        when(this.repository.findUserPreferencesByUserId(3L)).thenReturn(Optional.empty());

        // when & then
        assertThrows(UserNotFoundException.class, () -> this.service.getUserPreferences(3L));
    }

}