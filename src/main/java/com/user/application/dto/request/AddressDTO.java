package com.user.application.dto.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AddressDTO {

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
