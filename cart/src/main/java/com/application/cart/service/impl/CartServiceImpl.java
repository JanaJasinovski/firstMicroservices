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
    public CartItem addProductToCart(String productName, Long userId, Integer amount, String token) {
        ProductDto product = productClient.getProductByName(token, productName);

        Cart cart = this.getCartByUserId(userId);

        CartItem cartItem = cart.findCartItemByProductIdAndUserId(product.getId(), userId);

        if (cartItem != null) {
            cartItem.setUserId(userId);
            cartItem.setProductId(product.getId());
            cartItem.setAmount(amount);
            cartItem.setProductPrice(new BigDecimal(product.getPrice().intValue() * amount));
            cartItem = cartItemRepository.save(cartItem);

            redisTemplate.opsForHash().put(CART_ITEM, cartItem.getId(), cartItem);

        } else {
            cartItem = new CartItem();
            cartItem.setId(UUID.randomUUID().toString());
            cartItem.setUserId(userId);
            cartItem.setProductId(product.getId());
            cartItem.setAmount(amount);
            cartItem.setProductPrice(new BigDecimal(product.getPrice().intValue() * amount));
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
        return Cart.builder()
                .id(UUID.randomUUID().toString())
                .userId(userId)
                .cartItems(cartItemService.getCartItemsByUserId(userId))
                .build();
    }

    @Override
    public Cart getCartById(Long id) {
        return (Cart) redisTemplate.opsForHash().get(CART, id);
    }

    @Override
    public void clearCart(Long userId) {
        redisTemplate.opsForHash().delete(CART, userId);
    }

    @Override
    public void clearAllCart() {
        redisTemplate.delete(CART);
    }

}
