package com.user.application.mappers.response;

import com.user.application.dto.response.UserResponseDTO;
import com.user.domain.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = { AddressResponseMapper.class,
                UserProfilesResponseMapper.class,
                UserPreferencesResponseMapper.class})
public interface UserResponseMapper {

    @Mapping(source = "id", target = "userId")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "userProfiles", target = "userProfiles")
    @Mapping(source = "userPreferences", target = "userPreferences")
    UserResponseDTO fromUser(User user);
}
