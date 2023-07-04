package com.application.cart.service;

import com.application.cart.model.Cart;
import com.application.cart.model.CartItem;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {
    Cart getCartByUserId(Long userId);

    Cart getCartById(Long id);

    Integer getCartTotalAmount(Long userId);

    void clearCart(Long userId);

    CartItem addProductToCart(String productName, Long userId, String token);

    List<Cart> getAllCarts();
    void clearAllCart();

    Integer getTotalQuantity(Long userId);

    BigDecimal getTotalPrice(Long userId);
}
