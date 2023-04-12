package com.application.cart.service;

import com.application.cart.dto.CartDto;
import com.application.cart.model.Cart;

import java.util.List;

public interface CartService {
    CartDto addProductToCart(String productName, Long userId, Integer amount, String token);
    List<Cart> getAllCarts();
    Cart getCartById(String id);
    List<Cart> getCartByUserId(Long id);
    List<Cart> getCartByProductId(Long id);

    void deleteCartById(String id);
    void deleteCartByUserId(Long id);

}
