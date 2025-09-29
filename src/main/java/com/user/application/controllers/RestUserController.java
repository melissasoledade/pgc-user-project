package com.user.application.controllers;

import com.user.application.dto.request.UserDTO;
import com.user.application.dto.response.UserResponseDTO;
import com.user.application.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        log.info("Getting user information with id {}", id);
        final Optional<UserResponseDTO> response = userService.getUser(id);
        log.info("User {} with id: {}", response, id);

        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(empty());
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        log.info("Creating user {}", userDTO);
        Optional<UserResponseDTO> response = this.userService.createUser(userDTO);
        log.info("User created {}", response);

        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(empty());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
