package com.user.application.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AddressResponseDTO {

    private String addressName;
    private String number;
    private String complement;
    private String postalCode;
    private String neighbourhood;
    private String city;
    private String state;
    private String country;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
