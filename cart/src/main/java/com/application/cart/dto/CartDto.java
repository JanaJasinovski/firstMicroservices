package com.application.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private String cartId;
    private Long userId;
    private Long productId;
    private Integer amount;
    private BigDecimal totalPrice;
}