package com.application.cart.controller;

import com.application.cart.model.Cart;
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

    @PostMapping( "/{productName}/{amount}" )
    public void addProductToCart(@PathVariable String productName, @PathVariable Integer amount, HttpServletRequest request) {
        String token = "Bearer " + tokenProvider.getToken(request);
        cartService.addProductToCart(productName, tokenProvider.extractUser(request).getId(), amount, token);
    }

    @PostMapping("/{userId}")
    public void clearCartByUserId(@PathVariable Long userId) {
        cartService.deleteCartByUserId(userId);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }

    @PostMapping("/cart/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void clearCartById(@PathVariable String id) {
        cartService.deleteCartById(id);
    }

    @GetMapping("/cart/get/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void getCartById(@PathVariable String id) {
        cartService.getCartById(id);
    }

    @GetMapping("product/{productId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void getCartByProductId(@PathVariable Long productId) {
        cartService.getCartByProductId(productId);
    }

    @GetMapping("user/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void getCartByUserId(@PathVariable Long userId) {
        cartService.getCartByUserId(userId);
    }
}
