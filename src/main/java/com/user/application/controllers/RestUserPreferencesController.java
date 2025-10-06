package com.user.application.controllers;

import com.user.application.dto.response.UserPreferencesResponseDTO;
import com.user.application.services.UserPreferencesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/user/preferences")
public class RestUserPreferencesController {

    private final UserPreferencesService userPreferencesService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserPreferences(@PathVariable Long id) {
        final UserPreferencesResponseDTO response =
                this.userPreferencesService.getUserPreferences(id);
        log.info("Getting user preferences with userId {}", id);
        return ResponseEntity.ok(response);
    }
}
