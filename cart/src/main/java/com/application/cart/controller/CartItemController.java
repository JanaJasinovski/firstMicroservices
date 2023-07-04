package com.application.cart.controller;

import com.application.cart.model.Cart;
import com.application.cart.model.CartItem;
import com.application.cart.security.TokenProvider;
import com.application.cart.service.CartItemService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @DeleteMapping("/cartItem")
    public void clearCartItemById(@Param("id") String id, HttpServletRequest request) {
        cartItemService.removeCartItemById(id, tokenProvider.extractUser(request).getId());
    }

    @DeleteMapping("/cartItem/deleteAll")
    public void clearCartItemsByUserId(HttpServletRequest request) {
        cartItemService.removeAll(tokenProvider.extractUser(request).getId());
    }

    @PostMapping("/cartItem/increment")
    public List<CartItem> increment(@RequestParam("name") String name, HttpServletRequest request) {
        return cartItemService.incrementAmount(tokenProvider.extractUser(request).getId(), name);
    }

    @PostMapping("/cartItem/decrement")
    public List<CartItem> decrement(@RequestParam("name") String name, HttpServletRequest request) {
        return cartItemService.decrementAmount(tokenProvider.extractUser(request).getId(), name);
    }

    @GetMapping("/cartItem/totalPrice")
    public List<CartItem> totalPrice(HttpServletRequest request) {
        return cartItemService.totalPrice(tokenProvider.extractUser(request).getId());
    }

}
