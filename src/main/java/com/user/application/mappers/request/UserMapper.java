package com.user.application.mappers.request;

import com.user.application.dto.request.UserDTO;
import com.user.domain.entities.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        uses = { AddressMapper.class, UserProfilesMapper.class, UserPreferencesMapper.class })
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "address", target = "address")
    @Mapping(source = "userProfiles", target = "userProfiles")
    @Mapping(source = "userPreferences", target = "userPreferences")
    User toUser(UserDTO userDTO);

    @InheritInverseConfiguration
    UserDTO fromUser(User user);
}
