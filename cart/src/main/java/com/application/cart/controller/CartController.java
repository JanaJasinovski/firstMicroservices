package com.application.cart.controller;

import com.application.cart.model.Cart;
import com.application.cart.security.TokenProvider;
import com.application.cart.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping( "/carts" )
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final TokenProvider tokenProvider;

    @PostMapping( "/cart/create" )
    public void addProductToCart(@RequestParam("name") String name, HttpServletRequest request) {
        String token = "Bearer " + tokenProvider.getToken(request);
        cartService.addProductToCart(name, tokenProvider.extractUser(request).getId(), token);
    }

    @GetMapping( "/cartId" )
    public String getCartId(HttpServletRequest request) {
        return cartService.getCartByUserId(tokenProvider.extractUser(request).getId()).getId();
    }

    @GetMapping( "/cart/all" )
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }

    @GetMapping( "/cart/get/{id}" )
    public Cart getCartById(@PathVariable Long id) {
        return cartService.getCartById(id);
    }

    @GetMapping( "cart/userId" )
    public Cart getCartByUserId(HttpServletRequest request) {
        return cartService.getCartByUserId(tokenProvider.extractUser(request).getId());
    }

    @DeleteMapping("cart/clear")
    public void clearCart() {
        cartService.clearAllCart();
    }

    @GetMapping("/totalQuaninty")
    public Integer getTotalQuantity(HttpServletRequest request) {
        return cartService.getTotalQuantity(tokenProvider.extractUser(request).getId());
    }

    @GetMapping("/totalPrice")
    public BigDecimal getTotalPrice(HttpServletRequest request) {
        return cartService.getTotalPrice(tokenProvider.extractUser(request).getId());
    }
}
