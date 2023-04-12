package com.application.cart.model;

import com.application.cart.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Document( collection = "cart" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    private String  id;
    private Long userId;
    private Long productId;
    private BigDecimal totalPrice;
    private Integer amount;

    public Cart(Long userId, Long productId, BigDecimal totalPrice, Integer amount) {
        this.userId = userId;
        this.productId = productId;
        this.totalPrice = totalPrice;
        this.amount = amount;
    }
}
