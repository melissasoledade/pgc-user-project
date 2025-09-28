package com.user.infrastructure.repositories;

import com.user.domain.entities.UserPreferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaRepositoryUserPreferences extends JpaRepository<UserPreferences, Long> {

    @Query("SELECT u.userPreferences FROM User u WHERE u.id = :userId")
    Optional<UserPreferences> findUserPreferencesByUserId(@Param("userId") Long id);
}
