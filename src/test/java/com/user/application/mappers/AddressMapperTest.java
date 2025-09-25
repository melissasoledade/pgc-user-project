package com.user.application.mappers;

import com.user.application.dto.AddressDTO;
import com.user.domain.entities.Address;
import com.user.fixtures.application.AddressDTOHelper;
import com.user.fixtures.domain.AddressHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AddressMapperTest {

    private final AddressMapper mapper = AddressMapper.INSTANCE;

    @Test
    void shouldMapAddressDTOToAddressEntity() {
        // given
        final Address address = AddressHelper.defaultAddress().build();
        final AddressDTO addressDTO = AddressDTOHelper.defaultAddressDTO().build();

        // when
        final Address result = mapper.toAddress(addressDTO);

        // then
        assertNull(result.getAddressId());
        assertNull(result.getCreatedAt());
        assertNull(result.getUpdatedAt());
        assertEquals(address.getAddressName(), result.getAddressName());
        assertEquals(address.getNumber(), result.getNumber());
        assertEquals(address.getComplement(), result.getComplement());
        assertEquals(address.getPostalCode(), result.getPostalCode());
        assertEquals(address.getNeighbourhood(), result.getNeighbourhood());
        assertEquals(address.getCity(), result.getCity());
        assertEquals(address.getState(), result.getState());
        assertEquals(address.getCountry(), result.getCountry());
    }

    @Test
    void shouldMapAddressEntityToAddressDTO() {
        // given
        final Address address = AddressHelper.defaultAddress().build();
        final AddressDTO addressDTO = AddressDTOHelper.defaultAddressDTO().build();

        // when
        final AddressDTO result = mapper.fromAddress(address);

        // then
        assertEquals(addressDTO.getAddressName(), result.getAddressName());
        assertEquals(addressDTO.getNumber(), result.getNumber());
        assertEquals(addressDTO.getComplement(), result.getComplement());
        assertEquals(addressDTO.getPostalCode(), result.getPostalCode());
        assertEquals(addressDTO.getNeighbourhood(), result.getNeighbourhood());
        assertEquals(addressDTO.getCity(), result.getCity());
        assertEquals(addressDTO.getState(), result.getState());
        assertEquals(addressDTO.getCountry(), result.getCountry());
    }

}