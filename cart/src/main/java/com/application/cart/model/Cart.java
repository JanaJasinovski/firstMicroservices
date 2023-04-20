package com.application.cart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document( collection = "cart" )
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class Cart {
    @Id
    private String id;
    private Long userId;
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
