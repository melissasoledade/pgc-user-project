package com.user.application.services;

import com.user.application.dto.response.UserPreferencesResponseDTO;
import com.user.application.exceptionhandler.exceptions.UserNotFoundException;
import com.user.application.mappers.response.UserPreferencesResponseMapper;
import com.user.domain.entities.UserPreferences;
import com.user.domain.repositories.BaseUserPreferencesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserPreferencesService {

    private final BaseUserPreferencesRepository repository;
    private final UserPreferencesResponseMapper userPreferencesResponseMapper;

    public UserPreferencesResponseDTO getUserPreferences(Long id) {
        final Optional<UserPreferences> userPreferences =
                this.repository.findUserPreferencesByUserId(id);

        return userPreferences
                .map(this.userPreferencesResponseMapper::fromUserPreferences)
                .orElseThrow(UserNotFoundException::new);
    }
}
