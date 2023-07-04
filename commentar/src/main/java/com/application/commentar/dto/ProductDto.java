package com.application.commentar.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@JsonDeserialize
public class ProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private Long amount;
    private String picture;
    private String categoryName;
    private Long userId;
}
