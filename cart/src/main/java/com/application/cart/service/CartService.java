package com.application.cart.service;

import com.application.cart.model.Cart;
import com.application.cart.model.CartItem;

import java.util.List;

public interface CartService {
    Cart getCartByUserId(Long userId);

    CartItem findCartItemById(String cartItemId);

    Cart getCartById(String id);

    List<CartItem> getAllCartItems();
    void clearCart(Long userId);

    CartItem addProductToCart(String productName, Long userId, Integer amount, String token);

    void clearCartItemByUserId(Long userId);

    void removeCartItemById(String cartItemId);

    List<Cart> getAllCarts();
}
