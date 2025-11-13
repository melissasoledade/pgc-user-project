package com.user.application.mappers.event;

import com.user.application.dto.event.UserProfilesEvent;
import com.user.domain.entities.UserProfiles;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfilesEventMapper {

    @BeanMapping(ignoreUnmappedSourceProperties = { "userProfilesId", "createdAt", "updatedAt" })
    UserProfilesEvent fromUserProfiles(UserProfiles userProfiles);
}
