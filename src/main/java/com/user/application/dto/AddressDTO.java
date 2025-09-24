package com.user.application.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AddressDTO {

    private Long addressId;

    private String addressName;

    private String number;

    private String complement;

    private String postalCode;

    private String city;

    private String state;

    private String country;

    private BigDecimal latitude;

    private BigDecimal longitude;
}
