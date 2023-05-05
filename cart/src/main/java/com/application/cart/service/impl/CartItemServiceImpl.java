package com.application.cart.service.impl;

import com.application.cart.model.Cart;
import com.application.cart.model.CartItem;
//import com.application.cart.repository.CartItemRepository;
import com.application.cart.repository.CartItemRepository;
import com.application.cart.service.CartItemService;
import com.application.cart.service.CartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private static final String CART_ITEM = "CART_ITEM";
    private final RedisTemplate<String, Object> redisTemplate;
    private final CartItemRepository cartItemRepository;

    @Override
    public List<CartItem> getAllCartItems() {
        List<CartItem> cartItems = new ArrayList<>();
        List<Object> values = redisTemplate.opsForHash().values(CART_ITEM);
        for (Object obj : values) {
            if (obj instanceof CartItem) {
                CartItem cart = (CartItem) obj;
                cartItems.add(cart);
            }
        }
        return cartItems;
    }

    @Override
    public void clearCartItemByUserId(Long userId) {
        redisTemplate.opsForHash().delete(CART_ITEM, userId);

    }

    @Override
    public void removeCartItemById(String cartItemId) {
        redisTemplate.opsForHash().delete(CART_ITEM, cartItemId);
    }

    @Override
    public List<CartItem> getCartItemsByUserId(Long userId) {
//        return cartItemRepository.findAllByUserId(userId);
        List<CartItem> products = redisTemplate.opsForHash().values(CART_ITEM).stream()
                .map(obj -> (CartItem) obj)
                .toList();

        return products.stream()
                .filter(product -> product.getUserId().equals(userId))
                .toList();
    }


    @Override
    public CartItem findCartItemById(Long cartItemId) {
        return (CartItem) redisTemplate.opsForHash().get(CART_ITEM, cartItemId);
    }
}
