package com.user.application.mappers;

import com.user.application.dto.request.UserDTO;
import com.user.domain.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserUpdatedMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "address.addressId", ignore = true)
    @Mapping(target = "address.createdAt", ignore = true)
    @Mapping(target = "address.updatedAt", ignore = true)
    @Mapping(target = "userProfiles.userProfilesId", ignore = true)
    @Mapping(target = "userProfiles.createdAt", ignore = true)
    @Mapping(target = "userProfiles.updatedAt", ignore = true)
    @Mapping(target = "userPreferences.userPreferencesId", ignore = true)
    @Mapping(target = "userPreferences.createdAt", ignore = true)
    @Mapping(target = "userPreferences.updatedAt", ignore = true)
    void updateUserFromDTO(UserDTO userDTO, @MappingTarget User user);
}
