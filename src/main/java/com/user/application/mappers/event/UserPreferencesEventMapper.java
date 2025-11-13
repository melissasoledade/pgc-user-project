package com.user.application.mappers.event;

import com.user.application.dto.event.UserPreferencesEvent;
import com.user.domain.entities.UserPreferences;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPreferencesEventMapper {

    @BeanMapping(ignoreUnmappedSourceProperties = { "userPreferencesId", "createdAt", "updatedAt" })
    UserPreferencesEvent fromUserPreferences(UserPreferences userPreferences);

}
