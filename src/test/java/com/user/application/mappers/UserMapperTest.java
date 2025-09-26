package com.user.application.mappers;

import com.user.application.dto.UserDTO;
import com.user.domain.entities.User;
import com.user.fixtures.application.UserDTOHelper;
import com.user.fixtures.domain.UserHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private UserPreferencesMapper userPreferencesMapper;

    @Autowired
    private UserProfilesMapper userProfilesMapper;

    @Test
    void shouldMapUserDTOToUserEntity() throws ParseException {
        // given
        final User user = UserHelper.defaultUser().build();
        final UserDTO userDTO = UserDTOHelper.defaultUserDTO().build();

        // when
        final User result = userMapper.toUser(userDTO);

        // then
        assertEquals(user.getCpf(), result.getCpf());
        assertEquals(user.getGender(), result.getGender());
        assertEquals(user.getName(), result.getName());
        assertEquals(user.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(user.getBirthDate(), result.getBirthDate());

        assertEquals(user.getUserProfiles().getProfileCode(),
                result.getUserProfiles().getProfileCode());
        assertEquals(user.getUserProfiles().getProfileName(),
                result.getUserProfiles().getProfileName());

        assertEquals(user.getAddress().getAddressName(),
                result.getAddress().getAddressName());
        assertEquals(user.getAddress().getNumber(),
                result.getAddress().getNumber());
        assertNull(result.getAddress().getAddressId());

        assertEquals(user.getUserPreferences().getTimeZone(),
                result.getUserPreferences().getTimeZone());
        assertEquals(user.getUserPreferences().getLanguage(),
                result.getUserPreferences().getLanguage());
        assertNull(result.getUserPreferences().getUserPreferencesId());

        assertNull(result.getCreatedAt());
        assertNull(result.getId());
    }

    @Test
    void shouldMapUserEntityToUserDTO() throws ParseException {
        // given
        final User user = UserHelper.defaultUser().build();
        final UserDTO userDTO = UserDTOHelper.defaultUserDTO().build();

        // when
        final UserDTO result = userMapper.fromUser(user);

        // then
        assertEquals(userDTO.getCpf(), result.getCpf());
        assertEquals(userDTO.getGender(), result.getGender());
        assertEquals(userDTO.getName(), result.getName());
        assertEquals(userDTO.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(userDTO.getEmail(), result.getEmail());
        assertEquals(userDTO.getBirthDate(), result.getBirthDate());

        assertEquals(userDTO.getProfilesDTO().getProfileCode(),
                result.getProfilesDTO().getProfileCode());
        assertEquals(userDTO.getProfilesDTO().getProfileName(),
                result.getProfilesDTO().getProfileName());

        assertEquals(userDTO.getAddressDTO().getAddressName(),
                result.getAddressDTO().getAddressName());
        assertEquals(userDTO.getAddressDTO().getNumber(),
                result.getAddressDTO().getNumber());

        assertEquals(userDTO.getPreferencesDTO().getTimeZone(),
                result.getPreferencesDTO().getTimeZone());
        assertEquals(userDTO.getPreferencesDTO().getLanguage(),
                result.getPreferencesDTO().getLanguage());
    }

}
