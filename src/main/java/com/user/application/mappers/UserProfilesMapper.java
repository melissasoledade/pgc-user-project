package com.user.application.mappers;

import com.user.application.dto.request.UserProfilesDTO;
import com.user.domain.entities.UserProfiles;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserProfilesMapper {

    UserProfilesMapper INSTANCE = Mappers.getMapper(UserProfilesMapper.class);

    @Mapping(target = "userProfilesId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserProfiles toUserProfiles(UserProfilesDTO userProfilesDTO);

    @InheritInverseConfiguration
    UserProfilesDTO fromUserProfiles(UserProfiles userProfiles);
}
