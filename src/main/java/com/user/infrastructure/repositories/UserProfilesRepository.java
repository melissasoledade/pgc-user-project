package com.user.infrastructure.repositories;

import com.user.domain.entities.UserProfiles;
import com.user.domain.repositories.BaseUserProfilesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserProfilesRepository implements BaseUserProfilesRepository {

    private final JpaRepositoryUserProfiles repository;

    @Override
    public Optional<UserProfiles> findUserProfilesByUserId(Long id) {
        return this.repository.findUserProfilesByUserId(id);
    }
}
