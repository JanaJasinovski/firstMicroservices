package com.application.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {
    private String id;
    private Long userId;
    private Long productId;
    private String productName;
    private Long amount;
    private BigDecimal productPrice;
    private String picture;

}
