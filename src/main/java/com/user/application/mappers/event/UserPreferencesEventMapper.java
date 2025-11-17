package com.user.application.mappers.event;

import com.user.application.models.event.UserPreferencesEvent;
import com.user.domain.entities.UserPreferences;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPreferencesEventMapper {

    @BeanMapping(ignoreUnmappedSourceProperties = { "userPreferencesId", "createdAt", "updatedAt" })
    UserPreferencesEvent fromUserPreferences(UserPreferences userPreferences);

}
