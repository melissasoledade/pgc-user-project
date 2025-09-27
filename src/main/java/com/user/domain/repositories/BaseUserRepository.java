package com.user.domain.repositories;

import com.user.domain.entities.User;

import java.util.List;
import java.util.Optional;

public interface BaseUserRepository {

    User saveUser(User user);

    Optional<User> findUserById(Long id);

    List<User> findUsersByIds(List<Long> userIds);

    Boolean deleteUserById(Long id);
}
