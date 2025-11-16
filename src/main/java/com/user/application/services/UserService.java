package com.user.application.services;

import com.user.application.dto.EventType;
import com.user.application.dto.event.UserEvent;
import com.user.application.dto.request.UserDTO;
import com.user.application.dto.response.UserResponseDTO;
import com.user.application.exceptionhandler.exceptions.UserNotFoundException;
import com.user.application.exceptionhandler.exceptions.UsersNotFoundException;
import com.user.application.mappers.UserUpdatedMapper;
import com.user.application.mappers.event.UserEventMapper;
import com.user.application.mappers.request.UserMapper;
import com.user.application.mappers.response.UserResponseMapper;
import com.user.application.services.publisher.UserEventPublisher;
import com.user.domain.entities.User;
import com.user.domain.repositories.BaseUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserResponseMapper userResponseMapper;
    private final UserUpdatedMapper userUpdatedMapper;
    private final BaseUserRepository repository;
    private final UserEventPublisher publisher;
    private final UserEventMapper userEventMapper;

    private void publishUserEvent(User user, EventType eventType) {
        final UserEvent userEvent = this.userEventMapper.fromUser(user, eventType);
        this.publisher.publishMessage(userEvent);
    }

    private User updateUserAndSave(User user, UserDTO userDTO) {
        this.userUpdatedMapper.updateUserFromDTO(userDTO, user);
        return this.repository.saveUser(user);
    }

    public UserResponseDTO getUser(Long id) {
        final Optional<User> user = this.repository.findUserById(id);

        return user.map(this.userResponseMapper::fromUser)
                .orElseThrow(UserNotFoundException::new);
    }

    public List<UserResponseDTO> getUsers(List<Long> ids) {
        final List<User> users = this.repository.findUsersByIds(ids);

        if (users.isEmpty()) {
            throw new UsersNotFoundException();
        }

        return users.stream()
                .map(this.userResponseMapper::fromUser)
                .toList();
    }

    public Optional<UserResponseDTO> createUser(UserDTO userDTO) {
        final User user = this.userMapper.toUser(userDTO);
        final User savedUser = this.repository.saveUser(user);
        publishUserEvent(savedUser, EventType.CREATION);

        return Optional.of(this.userResponseMapper.fromUser(savedUser));
    }

    public Optional<UserResponseDTO> updateUser(Long id, UserDTO userDTO) {
        final Optional<User> user = this.repository.findUserById(id);

        if (user.isEmpty()) {
            return Optional.empty();
        }

        final User savedUser = updateUserAndSave(user.get(), userDTO);
        publishUserEvent(savedUser, EventType.UPDATE);

        return Optional.of(this.userResponseMapper.fromUser(savedUser));
    }

    public UserResponseDTO deleteUser(Long id) {
        final Optional<User> user = this.repository.deleteUserById(id);
        return user.map(this.userResponseMapper::fromUser)
                .orElseThrow(UserNotFoundException::new);
    }
}
