package com.user.application.services;

import com.user.application.dto.request.UserDTO;
import com.user.application.dto.response.UserResponseDTO;
import com.user.application.mappers.request.UserMapper;
import com.user.application.mappers.response.UserResponseMapper;
import com.user.domain.entities.User;
import com.user.domain.repositories.BaseUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserResponseMapper userResponseMapper;
    private final BaseUserRepository repository;

    public Optional<UserResponseDTO> getUser(Long id) {
        Optional<User> user = this.repository.findUserById(id);

        return user.map(this.userResponseMapper::fromUser);
    }

    public Optional<UserResponseDTO> createUser(UserDTO userDTO) {
        User user = this.userMapper.toUser(userDTO);
        User savedUser = this.repository.saveUser(user);

        return Optional.of(this.userResponseMapper.fromUser(savedUser));
    }

}
