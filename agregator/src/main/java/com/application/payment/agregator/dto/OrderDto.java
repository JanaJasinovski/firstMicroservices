package com.application.payment.agregator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private String id;

    private Long totalAmount;

    private BigDecimal totalPrice;

    private String orderStatus;

    private String dateCreated;

    private String deliveredDate;

    private Long userId;

    private List<CartItemDto> cartItems;
}
