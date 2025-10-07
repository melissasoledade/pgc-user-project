package com.user.application.services;

import com.user.application.dto.request.UserDTO;
import com.user.application.dto.response.UserResponseDTO;
import com.user.application.exceptionhandler.exceptions.UserNotFoundException;
import com.user.application.exceptionhandler.exceptions.UsersNotFoundException;
import com.user.application.mappers.UserUpdatedMapper;
import com.user.application.mappers.request.UserMapper;
import com.user.application.mappers.response.UserResponseMapper;
import com.user.domain.entities.User;
import com.user.domain.repositories.BaseUserRepository;
import com.user.fixtures.application.request.UserDTOHelper;
import com.user.fixtures.application.response.UserResponseDTOHelper;
import com.user.fixtures.domain.UserHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserResponseMapper userResponseMapper;

    @Mock
    private UserUpdatedMapper userUpdatedMapper;

    @Mock
    private BaseUserRepository repository;

    @InjectMocks
    private UserService service;

    @Test
    void shouldGetUserById() throws ParseException {
        // given
        final User user = UserHelper.defaultUser().build();
        final UserResponseDTO userResponseDTO = UserResponseDTOHelper.defaultUserResponseDTO().build();

        when(this.repository.findUserById(4L)).thenReturn(Optional.ofNullable(user));
        when(this.userResponseMapper.fromUser(user)).thenReturn(userResponseDTO);

        // when
        final UserResponseDTO result = this.service.getUser(4L);

        // then
        assertThat(result).isEqualTo(userResponseDTO);
        assertEquals(4L, result.getUserId());
        assertEquals("Ana da Silva", result.getName());
        assertEquals("SP", result.getAddress().getState());

        verify(this.repository, times(1)).findUserById(4L);
        verify(this.userResponseMapper, times(1)).fromUser(user);
    }

    @Test
    void shouldThrowUserNotFoundExceptionWhenUserNotFound() {
        // given
        when(this.repository.findUserById(4L)).thenReturn(Optional.empty());

        // when & then
        assertThrows(UserNotFoundException.class, () -> this.service.getUser(4L));
    }

    @Test
    void shouldGetUsersById() throws ParseException {
        // given
        final User firstUser = UserHelper.defaultUser().build();
        final User secondUser = UserHelper.defaultUser()
                .id(5L)
                .name("Beatriz Santos")
                .cpf("55555555555")
                .build();
        final UserResponseDTO firstUserResponse = UserResponseDTOHelper.defaultUserResponseDTO().build();
        final UserResponseDTO secondtUserResponse = UserResponseDTOHelper.defaultUserResponseDTO()
                .name("Beatriz Santos")
                .cpf("55555555555")
                .build();
        when(this.repository.findUsersByIds(List.of(4L, 5L))).thenReturn(List.of(firstUser, secondUser));
        when(this.userResponseMapper.fromUser(firstUser)).thenReturn(firstUserResponse);
        when(this.userResponseMapper.fromUser(secondUser)).thenReturn(secondtUserResponse);

        // when
        final List<UserResponseDTO> result = this.service.getUsers(List.of(4L, 5L));

        // then
        assertThat(result)
                .hasSize(2)
                .extracting(UserResponseDTO::getName)
                .containsExactlyInAnyOrder("Ana da Silva", "Beatriz Santos");

        verify(this.repository, times(1)).findUsersByIds(List.of(4L, 5L));
        verify(this.userResponseMapper, times(2)).fromUser(any());
    }

    @Test
    void shouldThrowUsersNotFoundExceptionWhenUsersNotFound() {
        // given
        when(this.repository.findUsersByIds(List.of(4L, 5L))).thenReturn(List.of());

        // when & then
        assertThrows(UsersNotFoundException.class, () -> this.service.getUsers(List.of(4L, 5L)));
    }

    @Test
    void shouldCreateUser() throws ParseException {
        // given
        final UserDTO userDTO = UserDTOHelper.defaultUserDTO()
                .build();
        final User user = UserHelper.defaultUser()
                .id(null)
                .build();
        final User savedUser = UserHelper.defaultUser()
                .id(1L)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        final UserResponseDTO userResponseDTO = UserResponseDTOHelper
                .defaultUserResponseDTO()
                .userId(1L)
                .build();

        when(this.userMapper.toUser(userDTO)).thenReturn(user);
        when(this.repository.saveUser(user)).thenReturn(savedUser);
        when(this.userResponseMapper.fromUser(savedUser)).thenReturn(userResponseDTO);

        // when
        final Optional<UserResponseDTO> result = this.service.createUser(userDTO);

        // then
        assertThat(result).isPresent();
        assertEquals(1L, result.get().getUserId());
        assertEquals("Ana da Silva", result.get().getName());

        verify(this.userMapper, times(1)).toUser(userDTO);
        verify(this.repository, times(1)).saveUser(user);
        verify(this.userResponseMapper, times(1)).fromUser(savedUser);
    }

    @Test
    void shouldUpdateUser() throws ParseException {
        // given
        final User user = UserHelper.defaultUser().build();
        final UserDTO userDTO = UserDTOHelper.defaultUserDTO()
                .email("ana-email-novo@gmail.com.br")
                .build();
        final User updatedUser = UserHelper.defaultUser()
                .email("ana-email-novo@gmail.com.br")
                .build();
        final UserResponseDTO userResponseDTO = UserResponseDTOHelper.defaultUserResponseDTO()
                .email("ana-email-novo@gmail.com.br")
                .build();

        when(this.repository.findUserById(4L)).thenReturn(Optional.ofNullable(user));
        when(this.repository.saveUser(updatedUser)).thenReturn(updatedUser);
        when(this.userResponseMapper.fromUser(updatedUser)).thenReturn(userResponseDTO);
        doAnswer(invocation -> {
            UserDTO dtoArg = invocation.getArgument(0);
            User userArg = invocation.getArgument(1);
            userArg.setEmail(dtoArg.getEmail());
            return null;
        }).when(this.userUpdatedMapper).updateUserFromDTO(userDTO, user);

        // when
        final Optional<UserResponseDTO> result = this.service.updateUser(4L, userDTO);

        // then
        assertThat(result)
                .isPresent()
                .get()
                .extracting(UserResponseDTO::getEmail)
                .isEqualTo("ana-email-novo@gmail.com.br");

        verify(this.userResponseMapper, times(1)).fromUser(user);
        verify(this.repository, times(1)).findUserById(4L);
        verify(this.repository, times(1)).saveUser(user);
        verify(this.userUpdatedMapper, times(1)).updateUserFromDTO(userDTO, user);
    }

    @Test
    void shouldDeleteUser() throws ParseException {
        // given
        final User user = UserHelper.defaultUser().build();
        final UserResponseDTO userResponseDTO = UserResponseDTOHelper.defaultUserResponseDTO().build();

        when(this.repository.deleteUserById(4L)).thenReturn(Optional.ofNullable(user));
        when(this.userResponseMapper.fromUser(user)).thenReturn(userResponseDTO);

        // when
        final UserResponseDTO result = this.service.deleteUser(4L);

        // then
        assertThat(result).extracting(UserResponseDTO::getUserId).isEqualTo(4L);
    }

}