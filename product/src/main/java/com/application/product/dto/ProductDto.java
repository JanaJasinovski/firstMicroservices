package com.application.product.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private Long amount;
    private String picture;
    private String categoryName;
    private Long userId;
}
