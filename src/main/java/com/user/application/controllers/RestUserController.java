package com.user.application.controllers;

import com.gravity9.jsonpatch.JsonPatch;
import com.user.application.models.request.UserDTO;
import com.user.application.models.response.UserResponseDTO;
import com.user.application.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/user")
public class RestUserController {

    private final UserService userService;

    @Operation(summary = "Get a user by id", description = "Fetches a user using the given id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        final UserResponseDTO response = userService.getUser(id);
        log.info("Getting User with id {}", id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get a list of users by ids", description = "Fetches a list of users using the list of ids")
    @GetMapping("/list")
    public ResponseEntity<?> getUsers(@RequestParam List<Long> ids) {
        final List<UserResponseDTO> response = userService.getUsers(ids);
        log.info("Getting users with ids {}", ids);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Create a new user", description = "Create a new user with the UserDTO")
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        Optional<UserResponseDTO> response = this.userService.createUser(userDTO);

        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(empty());
        }

        log.info("User created with id {}", response.get().getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Update a user by id", description = "Update a user using the given id")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        Optional<UserResponseDTO> response = this.userService.updateUser(id, userDTO);

        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(empty());
        }

        log.info("User updated with id {}", response.get().getUserId());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Partially update a user by id",
            description = "Performs a partial update on a user by updating only the fields present in the request body")
    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<?> patchUser(@PathVariable Long id, @RequestBody JsonPatch patch) {
        Optional<UserResponseDTO> response = this.userService.patchUser(id, patch);

        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(empty());
        }

        log.info("User fields updated with id {}", response.get().getUserId());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Delete a user by id", description = "Delete a user using the given id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        UserResponseDTO response = this.userService.deleteUser(id);
        log.info("User deleted with id {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
