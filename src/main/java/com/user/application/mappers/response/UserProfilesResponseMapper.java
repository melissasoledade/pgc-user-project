package com.user.application.mappers.response;

import com.user.application.models.response.UserProfilesResponseDTO;
import com.user.domain.entities.UserProfiles;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfilesResponseMapper {

    @BeanMapping(ignoreUnmappedSourceProperties = { "userProfilesId", "createdAt", "updatedAt" })
    UserProfilesResponseDTO fromUserProfiles(UserProfiles userProfiles);
}
