package com.user.application.controllers;

import com.user.application.dto.request.UserDTO;
import com.user.application.dto.response.UserResponseDTO;
import com.user.application.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/user")
public class RestUserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        final UserResponseDTO response = userService.getUser(id);
        log.info("Getting User with id {}", id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getUsers(@RequestParam List<Long> ids) {
        final List<UserResponseDTO> response = userService.getUsers(ids);
        log.info("Getting users with ids {}", ids);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        Optional<UserResponseDTO> response = this.userService.createUser(userDTO);

        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(empty());
        }

        log.info("User created with id {}", response.get().getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        Optional<UserResponseDTO> response = this.userService.updateUser(id, userDTO);

        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(empty());
        }

        log.info("User updated with id {}", response.get().getUserId());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        UserResponseDTO response = this.userService.deleteUser(id);
        log.info("User deleted with id {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
