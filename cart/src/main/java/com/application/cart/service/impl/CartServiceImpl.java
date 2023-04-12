package com.application.cart.service.impl;

import com.application.cart.dto.CartDto;
import com.application.cart.dto.ProductDto;
import com.application.cart.feignClient.ProductClient;
import com.application.cart.model.Cart;
import com.application.cart.repository.CartRepository;
import com.application.cart.security.TokenProvider;
import com.application.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;
    private final ProductClient productClient;

    @Override
    public CartDto addProductToCart(String productName, Long userId, Integer amount, String token) {
        ProductDto product = productClient.getProductByName(token, productName);

        Cart cart = new Cart(
                userId,
                product.getId(),
                new BigDecimal(product.getPrice().intValue() * amount),
                amount
               );

        cartRepository.save(cart);
        return modelMapper.map(cart, CartDto.class);
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }


    @Override
    public List<Cart> getCartByProductId(Long id) {
        return cartRepository.findCartByProductId(id);
    }

    @Override
    public void deleteCartById(String id) {
        cartRepository.deleteById(id);
    }

    @Override
    public void deleteCartByUserId(Long id) {
        cartRepository.deleteCartByUserId(id);
    }

    @Override
    public Cart getCartById(String id) {
        return cartRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Cart> getCartByUserId(Long id) {
        return cartRepository.findCartByUserId(id);
    }
}
