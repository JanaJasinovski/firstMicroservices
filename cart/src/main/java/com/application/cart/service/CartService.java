package com.application.cart.service;

import com.application.cart.dto.CartDto;
import com.application.cart.model.Cart;

import java.util.List;

public interface CartService {
    CartDto addProductToCart(String productName, String username, Integer quantity);

    List<Cart> getAllCarts();

    void clearCart();

    void deleteProductFromCart(String productName);
}
