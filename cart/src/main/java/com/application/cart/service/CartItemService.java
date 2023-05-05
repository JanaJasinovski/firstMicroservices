package com.application.cart.service;

import com.application.cart.model.CartItem;

import java.util.List;

public interface CartItemService {

    CartItem findCartItemById(Long cartItemId);
    List<CartItem> getAllCartItems();
    void clearCartItemByUserId(Long userId);
    void removeCartItemById(String cartItemId);
    List<CartItem> getCartItemsByUserId(Long userId);
}
