package com.user.infrastructure.repositories;

import com.user.domain.entities.UserProfiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaRepositoryUserProfiles extends JpaRepository<UserProfiles, Long> {

    @Query("SELECT u.userProfiles FROM User u WHERE u.id = :userId")
    Optional<UserProfiles> findUserProfilesByUserId(@Param("userId") Long id);
}
