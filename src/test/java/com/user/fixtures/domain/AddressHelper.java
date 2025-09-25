package com.user.fixtures.domain;

import com.user.domain.entities.Address;

import java.math.BigDecimal;

public class AddressHelper {

    public static Address.AddressBuilder defaultAddress() {
        return Address.builder()
                .addressId(1L)
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
