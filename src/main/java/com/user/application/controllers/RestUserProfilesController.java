package com.user.application.controllers;

import com.user.application.dto.response.UserProfilesResponseDTO;
import com.user.application.services.UserProfilesService;
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
@RequestMapping("/user/profiles")
public class RestUserProfilesController {

    private final UserProfilesService userProfilesService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserProfiles(@PathVariable Long id) {
        final UserProfilesResponseDTO response = userProfilesService.getUserProfiles(id);
        log.info("Getting user profiles with userId {}", id);
        return ResponseEntity.ok(response);
    }
}
