package com.user.domain.repositories;

import com.user.domain.entities.UserPreferences;

import java.util.Optional;

public interface BaseUserPreferencesRepository {

    Optional<UserPreferences> findUserPreferencesByUserId(Long id);
}
