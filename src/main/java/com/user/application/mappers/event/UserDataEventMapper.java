package com.user.application.mappers.event;

import com.user.application.dto.event.UserDataEvent;
import com.user.domain.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {UserProfilesEventMapper.class,
                UserPreferencesEventMapper.class,
                UserAddressEventMapper.class})
public interface UserDataEventMapper {

    @Mapping(source = "id", target = "userId")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "userProfiles", target = "userProfiles")
    @Mapping(source = "userPreferences", target = "userPreferences")
    UserDataEvent fromUser(User user);
}
