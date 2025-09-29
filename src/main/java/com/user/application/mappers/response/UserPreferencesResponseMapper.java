package com.user.application.mappers.response;

import com.user.application.dto.response.UserPreferencesResponseDTO;
import com.user.domain.entities.UserPreferences;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPreferencesResponseMapper {

    @BeanMapping(ignoreUnmappedSourceProperties = { "userPreferencesId", "createdAt", "updatedAt" })
    UserPreferencesResponseDTO fromUserPreferences(UserPreferences userPreferences);
}
