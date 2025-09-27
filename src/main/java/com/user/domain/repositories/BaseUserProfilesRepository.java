package com.user.domain.repositories;

import com.user.domain.entities.UserProfiles;

import java.util.Optional;

public interface BaseUserProfilesRepository {

    Optional<UserProfiles> findUserProfilesByUserId(Long id);
}
