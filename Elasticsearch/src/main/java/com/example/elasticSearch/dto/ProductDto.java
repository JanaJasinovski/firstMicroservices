package com.example.elasticSearch.dto;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

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
