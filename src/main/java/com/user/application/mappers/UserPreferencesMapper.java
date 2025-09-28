package com.user.application.mappers;

import com.user.application.dto.request.UserPreferencesDTO;
import com.user.domain.entities.UserPreferences;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserPreferencesMapper {

    UserPreferencesMapper INSTANCE = Mappers.getMapper(UserPreferencesMapper.class);

    @Mapping(target = "userPreferencesId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserPreferences toUserPreferences(UserPreferencesDTO userPreferencesDTO);

    @InheritInverseConfiguration
    UserPreferencesDTO fromUserPreferences(UserPreferences userPreferences);
}
