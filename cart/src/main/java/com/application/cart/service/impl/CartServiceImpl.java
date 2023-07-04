package com.application.cart.service.impl;

import com.application.cart.dto.ProductDto;
import com.application.cart.feignClient.ProductClient;
import com.application.cart.model.Cart;
import com.application.cart.model.CartItem;
import com.application.cart.repository.CartItemRepository;
import com.application.cart.repository.CartRepository;
import com.application.cart.service.CartItemService;
import com.application.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private static final String CART_ITEM = "CART_ITEM";
    private static final String CART = "CART";
    private final CartItemService cartItemService;
    private final ProductClient productClient;
    private final RedisTemplate<String, Object> redisTemplate;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    @Override
    public CartItem addProductToCart(String productName, Long userId, String token) {
        ProductDto product = productClient.getProductByName(token, productName);
//        List<ProductDto> productList = (List<ProductDto>) product.getContent();

        Cart cart = this.getCartByUserId(userId);
//
//        Long productId = null;
//        BigDecimal productPrice = null;
//        for (ProductDto pr : productList) {
//            productId = pr.getId();
//            productPrice = pr.getPrice();
//        }

        CartItem cartItem = cart.findCartItemByProductIdAndUserId(product.getId(), userId);

        if (cartItem != null) {
            cartItem.setUserId(userId);
            cartItem.setProductId(product.getId());
            cartItem.setProductName(productName);
            cartItem.setAmount(1);
            cartItem.setProductPrice(new BigDecimal(product.getPrice().intValue()));
            cartItem.setPicture(product.getPicture());
            cartItem = cartItemRepository.save(cartItem);

            redisTemplate.opsForHash().put(CART_ITEM, cartItem.getId(), cartItem);

        } else {
            cartItem = new CartItem();
            cartItem.setId(UUID.randomUUID().toString());
            cartItem.setUserId(userId);
            cartItem.setProductId(product.getId());
            cartItem.setProductName(productName);
            cartItem.setAmount(1);
            cartItem.setProductPrice(new BigDecimal(product.getPrice().intValue()));
            cartItem.setPicture(product.getPicture());
            cart.setCartItems(Collections.singletonList(cartItem));
//            cartRepository.save(cart);
//            cartItem = cartItemRepository.save(cartItem);
            redisTemplate.opsForHash().put(CART_ITEM, cartItem.getId(), cartItem);
        }

        return cartItem;
    }

    @Override
    public List<Cart> getAllCarts() {
        return (List<Cart>) redisTemplate.opsForHash().entries(CART);
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        List<CartItem> cartItems = cartItemService.getCartItemsByUserId(userId);
        int totalAmount = cartItems.stream()
                .mapToInt(CartItem::getAmount)
                .sum();

        BigDecimal totalProductPrice = cartItems.stream()
                .map(item -> item.getProductPrice().multiply(BigDecimal.valueOf(item.getAmount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return Cart.builder()
                .id(UUID.randomUUID().toString())
                .userId(userId)
                .totalQuantity(totalAmount)
                .totalPrice(new BigDecimal(totalProductPrice.intValue() * totalAmount))
                .cartItems(cartItemService.getCartItemsByUserId(userId))
                .build();
    }

    @Override
    public Cart getCartById(Long id) {
        return (Cart) redisTemplate.opsForHash().get(CART, id);
    }

    @Override
    public Integer getCartTotalAmount(Long userId) {
        Cart cartByUserId = getCartByUserId(userId);
        return cartByUserId.getTotalQuantity();
    }

    @Override
    public void clearCart(Long userId) {
        redisTemplate.opsForHash().delete(CART, userId);
    }

    @Override
    public void clearAllCart() {
        redisTemplate.delete(CART);
    }

    @Override
    public Integer getTotalQuantity(Long userId) {
        Cart cart = getCartByUserId(userId);
        return cart.getTotalQuantity();
    }

    @Override
    public BigDecimal getTotalPrice(Long userId) {
        Cart cart = getCartByUserId(userId);
        return cart.getTotalPrice();
    }

}
