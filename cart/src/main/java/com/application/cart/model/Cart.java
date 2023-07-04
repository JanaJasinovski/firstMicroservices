package com.application.cart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash("CART")
public class Cart implements Serializable {
    private String id;
    private Long userId;
    private Integer totalQuantity;
    private BigDecimal totalPrice;
    private List<CartItem> cartItems;

    public Cart(Long userId, List<CartItem> cartItems) {
        this.userId = userId;
        this.cartItems = cartItems;
    }

    public CartItem findCartItemByProductIdAndUserId(Long productId, Long userId) {
        for (CartItem item : this.cartItems) {
            if (item.getProductId().equals(productId) && item.getUserId().equals(userId)) {
                return item;
            }
        }
        return null;
    }

}
