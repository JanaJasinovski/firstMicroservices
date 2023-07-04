package com.application.payment.agregator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentOrderDto {
    private Long id;

    private String orderTrackingNumber;

    private UserDto customer;

    private AddressDto shippingAddress;

    private OrderDto order;

    private List<CartItemDto> orderItems;

    private List<PaymentAttemptDto> paymentAttempts;
}
