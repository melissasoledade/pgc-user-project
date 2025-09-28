package com.user.fixtures.application.response;

import com.user.application.dto.response.AddressResponseDTO;

import java.math.BigDecimal;

public class AddressResponseDTOHelper {

    public static AddressResponseDTO.AddressResponseDTOBuilder defaultAddressResponseDTO() {
        return AddressResponseDTO.builder()
                .addressName("Avenida dos Estados")
                .number("123")
                .complement("Apto 55 Bloco A")
                .postalCode("09280-560")
                .neighbourhood("Bangu")
                .city("Santo Andre")
                .state("SP")
                .country("Brasil")
                .latitude(BigDecimal.valueOf(-23.644231))
                .longitude(BigDecimal.valueOf(-46.5285065));
    }
}
