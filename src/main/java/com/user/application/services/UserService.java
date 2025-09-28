package com.user.application.services;

import com.user.application.dto.request.UserDTO;
import com.user.application.mappers.UserMapper;
import com.user.domain.entities.User;
import com.user.domain.repositories.BaseUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final BaseUserRepository repository;

    public Optional<UserDTO> getUser(Long id) {
        Optional<User> user = this.repository.findUserById(id);

        return user.map(this.userMapper::fromUser);
    }

    public Optional<UserDTO> createUser(UserDTO userDTO) {
        User user = this.userMapper.toUser(userDTO);
        User savedUser = this.repository.saveUser(user);

        return Optional.of(this.userMapper.fromUser(savedUser));
    }

}
