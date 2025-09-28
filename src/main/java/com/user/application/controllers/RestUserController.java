package com.user.application.controllers;

import com.user.application.dto.request.UserDTO;
import com.user.application.dto.response.UserResponseDTO;
import com.user.application.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
            return ResponseEntity.ok("No user found with id");
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        log.info("Creating user {}", userDTO);
        Optional<UserResponseDTO> response = this.userService.createUser(userDTO);
        log.info("User created {}", response);

        if (response.isEmpty()) {
            return ResponseEntity.ok("User not created");
        }

        return ResponseEntity.ok(response.get());
    }

}
