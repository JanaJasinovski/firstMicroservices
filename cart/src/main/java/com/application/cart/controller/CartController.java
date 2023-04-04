package com.application.cart.controller;

import com.application.cart.feignClient.ProductClient;
import com.application.cart.model.Cart;
import com.application.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( "/carts" )
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping( "/{productName}/{username}/{amount}" )
    public void addProductToCart(@PathVariable String productName, @PathVariable String username, @PathVariable Integer amount) {
        cartService.addProductToCart(productName, username, amount);
    }

    @PostMapping( "/{productName}" )
    public void deleteProductFromCart(@PathVariable String productName) {
        cartService.deleteProductFromCart(productName);
    }

    @PostMapping()
    public void clearCart() {
        cartService.clearCart();
    }

    @GetMapping()
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }
}
