package com.user.infrastructure.repositories;

import com.user.domain.entities.User;
import com.user.domain.repositories.BaseUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
@RequiredArgsConstructor
public class UserJpaRepository implements BaseUserRepository {

    private final SpringDataJpaRepository repository;

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
    public void deleteUserById(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        this.repository.deleteAll();
    }
}
