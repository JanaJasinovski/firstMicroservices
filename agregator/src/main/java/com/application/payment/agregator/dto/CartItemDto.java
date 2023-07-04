package com.application.payment.agregator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    private String id;
    private Long userId;
    private Long productId;
    private String productName;
    private Integer amount;
    private BigDecimal productPrice;
    private String picture;
}
