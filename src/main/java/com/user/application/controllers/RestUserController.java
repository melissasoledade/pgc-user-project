package com.user.application.controllers;

import com.user.application.dto.request.UserDTO;
import com.user.application.dto.response.UserResponseDTO;
import com.user.application.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        final Optional<UserResponseDTO> response = userService.getUser(id);

        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(empty());
        }

        log.info("Getting User with id: {}", id);
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

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
