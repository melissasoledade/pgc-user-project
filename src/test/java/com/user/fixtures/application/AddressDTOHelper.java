package com.user.fixtures.application;

import com.user.application.dto.request.AddressDTO;

import java.math.BigDecimal;

public class AddressDTOHelper {

    public static AddressDTO.AddressDTOBuilder defaultAddressDTO() {
        return AddressDTO.builder()
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
