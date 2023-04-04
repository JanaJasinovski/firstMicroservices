package com.application.cart.service.impl;

import com.application.cart.dto.CartDto;
import com.application.cart.dto.ProductDto;
import com.application.cart.dto.UserDto;
import com.application.cart.feignClient.ProductClient;
import com.application.cart.feignClient.UserClient;
import com.application.cart.model.Cart;
import com.application.cart.repository.CartRepository;
import com.application.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;
    private final ProductClient productClient;
    private final UserClient userClient;

    @Override
    public CartDto addProductToCart(String productName, String username, Integer amount) {
        ProductDto product = productClient.getProductByName(productName);
        UserDto user = userClient.findUserByUsername(username);

        Cart cart = new Cart(
                user.getUsername(),
                product.getName(),
                amount,
                new BigDecimal(product.getPrice().intValue() * amount));

        cartRepository.save(cart);

        product.setAmount(product.getAmount() - amount);
        return modelMapper.map(cart, CartDto.class);
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public void clearCart() {
        cartRepository.deleteAll();
    }

    @Override
    public void deleteProductFromCart(String productName) {
        cartRepository.deleteByProductName(productName);
    }
}
