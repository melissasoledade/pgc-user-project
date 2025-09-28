package com.user.application.controllers;

import com.user.application.dto.request.UserDTO;
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
        final Optional<UserDTO> userDTO = userService.getUser(id);
        log.info("User {} with id: {}", userDTO, id);

        if (userDTO.isEmpty()) {
            return ResponseEntity.ok("No user found with id");
        }

        return ResponseEntity.ok(userDTO);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        log.info("Creating user {}", userDTO);
        Optional<UserDTO> result = this.userService.createUser(userDTO);
        log.info("User created {}", result);

        if (result.isEmpty()) {
            return ResponseEntity.ok("User not created");
        }

        return ResponseEntity.ok(result.get());
    }

}
