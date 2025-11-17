package com.user.application.mappers.event;

import com.user.application.models.event.UserAddressEvent;
import com.user.domain.entities.Address;
import com.user.fixtures.application.event.UserAddressEventHelper;
import com.user.fixtures.domain.AddressHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserAddressEventMapperTest {

    @Autowired
    private UserAddressEventMapper mapper;

    @Test
    void shouldMapAEntityToEvent() {
        // given
        final Address address = AddressHelper.defaultAddress().build();
        final UserAddressEvent event = UserAddressEventHelper.defaultUserAddressEvent().build();

        // when
        final UserAddressEvent result = this.mapper.fromAddress(address);

        // then
        assertEquals(event.getAddressName(), result.getAddressName());
        assertEquals(event.getNumber(), result.getNumber());
        assertEquals(event.getComplement(), result.getComplement());
        assertEquals(event.getCountry(), result.getCountry());
        assertEquals(event.getState(), result.getState());
        assertEquals(event.getNeighbourhood(), result.getNeighbourhood());
        assertEquals(event.getPostalCode(), result.getPostalCode());
        assertEquals(event.getLatitude(), result.getLatitude());
        assertEquals(event.getLongitude(), result.getLongitude());
    }

}