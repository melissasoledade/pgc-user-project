package com.user.infrastructure.repositories;

import com.user.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRepositoryUser extends JpaRepository<User, Long> {
}
