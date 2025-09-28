package com.user.infrastructure.repositories;

import com.user.domain.entities.UserPreferences;
import com.user.domain.repositories.BaseUserPreferencesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserPreferencesRepository implements BaseUserPreferencesRepository {

    private final JpaRepositoryUserPreferences repository;

    @Override
    public Optional<UserPreferences> findUserPreferencesByUserId(Long id) {
        return this.repository.findUserPreferencesByUserId(id);
    }
}
