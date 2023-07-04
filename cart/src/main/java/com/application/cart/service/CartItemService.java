package com.application.cart.service;

import com.application.cart.model.CartItem;

import java.util.List;

public interface CartItemService {

    CartItem findCartItemById(Long cartItemId);
    List<CartItem> getAllCartItems();
    void clearCartItemByUserId(Long userId);
    List<CartItem> removeCartItemById(String cartItemId, Long userId);

    List<CartItem> removeAll(Long userId);
    List<CartItem> getCartItemsByUserId(Long userId);
    List<CartItem> incrementAmount(Long userId, String productName);
    List<CartItem> decrementAmount(Long userId, String productName);
    List<CartItem> totalPrice(Long userId);
}
