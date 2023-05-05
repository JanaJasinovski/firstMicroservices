package com.application.cart.service;

import com.application.cart.model.Cart;
import com.application.cart.model.CartItem;

import java.util.List;

public interface CartService {
    Cart getCartByUserId(Long userId);

    Cart getCartById(Long id);

    void clearCart(Long userId);

    CartItem addProductToCart(String productName, Long userId, Integer amount, String token);

    List<Cart> getAllCarts();
    public void clearAllCart();
}
