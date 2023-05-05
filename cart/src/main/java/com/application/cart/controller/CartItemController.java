package com.application.cart.controller;

import com.application.cart.model.CartItem;
import com.application.cart.security.TokenProvider;
import com.application.cart.service.CartItemService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( "/cartItems" )
@RequiredArgsConstructor
public class CartItemController {
    private final CartItemService cartItemService;
    private final TokenProvider tokenProvider;

    @GetMapping( "/cartItem/userId" )
    public List<CartItem> getCartItemByUserID(HttpServletRequest request) {
        return cartItemService.getCartItemsByUserId(tokenProvider.extractUser(request).getId());
    }

    @GetMapping( "/cartItem/all" )
    public List<CartItem> getAllCartItems() {
        return cartItemService.getAllCartItems();
    }

    @GetMapping( "/cartItem/get/{id}" )
    public CartItem getCartItemById(@PathVariable Long id) {
        return cartItemService.findCartItemById(id);
    }

    @DeleteMapping("/cartItem/clear/userId")
    public void clearCartItemByUserId(HttpServletRequest request) {
        cartItemService.clearCartItemByUserId(tokenProvider.extractUser(request).getId());
    }
    @PostMapping("/cartItem/{id}")
    public void clearCartItemById(@PathVariable String id) {
        cartItemService.removeCartItemById(id);
    }

}
