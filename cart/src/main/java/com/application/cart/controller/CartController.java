package com.application.cart.controller;

import com.application.cart.model.Cart;
import com.application.cart.model.CartItem;
import com.application.cart.security.TokenProvider;
import com.application.cart.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( "/carts" )
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final TokenProvider tokenProvider;

    @PostMapping( "/create/{productName}/{amount}" )
    public void addProductToCart(@PathVariable String productName, @PathVariable Integer amount, HttpServletRequest request) {
        String token = "Bearer " + tokenProvider.getToken(request);
        cartService.addProductToCart(productName, tokenProvider.extractUser(request).getId(), amount, token);
    }

    @GetMapping( "/cart/all" )
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping( "/cartItem/all" )
    public List<CartItem> getAllCartItems() {
        return cartService.getAllCartItems();
    }


    @GetMapping( "/cart/get/{id}" )
    public Cart getCartById(@PathVariable String id) {
        return cartService.getCartById(id);
    }

    @GetMapping( "/cartItem/get/{id}" )
    public CartItem getCartItemById(@PathVariable String id) {
        return cartService.findCartItemById(id);
    }

    @GetMapping( "cart/userId" )
    public Cart getCartByUserId(HttpServletRequest request) {
        return cartService.getCartByUserId(tokenProvider.extractUser(request).getId());
    }

    @PostMapping("/cartItem/clear/userId")
    public void clearCartItemByUserId(HttpServletRequest request) {
        cartService.clearCartItemByUserId(tokenProvider.extractUser(request).getId());
    }

    @PostMapping("/cart/{id}")
    public void clearCartById(@PathVariable String id) {
        cartService.removeCartItemById(id);
    }

//    @PostMapping("/cart/deleteAll")
//    public void clearCartByUserId(HttpServletRequest request) {
//        cartService.clearCart(tokenProvider.extractUser(request).getId());
//    }

}
