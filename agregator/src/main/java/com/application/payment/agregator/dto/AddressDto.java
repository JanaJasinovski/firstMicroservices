package com.application.payment.agregator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private Long id;
    private String country;

    private String state;

    private String city;

    private String street;

    private String zipCode;

    private Long userId;
}
