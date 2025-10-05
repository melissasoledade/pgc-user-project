package com.user.infrastructure.repositories;

import com.user.domain.entities.User;
import com.user.domain.repositories.BaseUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository implements BaseUserRepository {

    private final JpaRepositoryUser repository;

    @Override
    public User saveUser(User user) {
        return this.repository.save(user);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public List<User> findUsersByIds(List<Long> userIds) {
        return this.repository.findAllById(userIds);
    }

    @Override
    public Optional<User> deleteUserById(Long id) {
        Optional<User> user = this.repository.findById(id);

        if (user.isEmpty()) {
            return Optional.empty();
        }

        this.repository.deleteById(id);
        return user;
    }

    @Override
    public void deleteAll() {
        this.repository.deleteAll();
    }
}
