package com.application.cart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document( collection = "cart_item" )
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartItem {
    @Id
    private String id;
    @JsonIgnore
    private Long userId;
    private Long productId;
    private Integer amount;
    private BigDecimal productPrice;

    public CartItem(Long userId, Long productId, Integer amount, BigDecimal productPrice) {
        this.userId = userId;
        this.productId = productId;
        this.amount = amount;
        this.productPrice = productPrice;
    }
}
