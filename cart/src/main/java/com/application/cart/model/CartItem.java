package com.application.cart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash( "CART_ITEM" )
public class CartItem implements Serializable {
    @Serial
    private static final long serialVersionUID = 5811817633994165060L;
    private String id;
    private Long userId;
    private Long productId;
    private Integer amount;
    private BigDecimal productPrice;

}
