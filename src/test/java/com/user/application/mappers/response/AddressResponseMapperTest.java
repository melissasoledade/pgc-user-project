package com.user.application.mappers.response;

import com.user.application.dto.response.AddressResponseDTO;
import com.user.domain.entities.Address;
import com.user.fixtures.application.response.AddressResponseDTOHelper;
import com.user.fixtures.domain.AddressHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AddressResponseMapperTest {

    @Autowired
    private AddressResponseMapper mapper;

    @Test
    void shouldMapEntityToAddressResponseDTO() {
        // given
        final AddressResponseDTO addressResponseDTO = AddressResponseDTOHelper
                .defaultAddressResponseDTO().build();
        final Address address = AddressHelper.defaultAddress().build();

        // when
        final AddressResponseDTO result = this.mapper.fromAddress(address);

        // then
        assertEquals(addressResponseDTO.getAddressName(), result.getAddressName());
        assertEquals(addressResponseDTO.getNumber(), result.getNumber());
        assertEquals(addressResponseDTO.getComplement(), result.getComplement());
        assertEquals(addressResponseDTO.getCountry(), result.getCountry());
        assertEquals(addressResponseDTO.getState(), result.getState());
        assertEquals(addressResponseDTO.getNeighbourhood(), result.getNeighbourhood());
        assertEquals(addressResponseDTO.getPostalCode(), result.getPostalCode());
        assertEquals(addressResponseDTO.getLatitude(), result.getLatitude());
        assertEquals(addressResponseDTO.getLongitude(), result.getLongitude());
    }


}