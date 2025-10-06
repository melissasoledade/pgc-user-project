package com.user.application.services;

import com.user.application.dto.response.UserProfilesResponseDTO;
import com.user.application.exceptionhandler.exceptions.UserNotFoundException;
import com.user.application.mappers.response.UserProfilesResponseMapper;
import com.user.domain.entities.UserProfiles;
import com.user.domain.repositories.BaseUserProfilesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserProfilesService {

    private final BaseUserProfilesRepository repository;
    private final UserProfilesResponseMapper userProfilesResponseMapper;

    public UserProfilesResponseDTO getUserProfiles(Long id) {
        final Optional<UserProfiles> userProfiles = this.repository.findUserProfilesByUserId(id);

        return userProfiles.map(this.userProfilesResponseMapper::fromUserProfiles)
                .orElseThrow(UserNotFoundException::new);
    }
}
