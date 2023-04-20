package com.application.cart.service.impl;

import com.application.cart.dto.ProductDto;
import com.application.cart.feignClient.ProductClient;
import com.application.cart.model.Cart;
import com.application.cart.model.CartItem;
import com.application.cart.repository.CartItemRepository;
import com.application.cart.repository.CartRepository;
import com.application.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;
    private final ProductClient productClient;

    @Override
    public CartItem addProductToCart(String productName, Long userId, Integer amount, String token) {
        ProductDto product = productClient.getProductByName(token, productName);

        Cart cart = this.getCartByUserId(userId);

        CartItem cartItem = cart.findCartItemByProductIdAndUserId(product.getId(), userId); //new CartItem(userId, product.getId(), amount, new BigDecimal(product.getPrice().intValue() * amount));

        if(product.getAmount() <= 0 || product.getAmount() - amount <=0 ) {
            System.out.println("Too little products");
        }

        if(cartItem != null) {
            cartItem.setUserId(userId);
            cartItem.setProductId(product.getId());
            cartItem.setAmount(amount);
            cartItem.setProductPrice(new BigDecimal(product.getPrice().intValue() * amount));
            cartItem = cartItemRepository.save(cartItem);
        } else {
            cartItem = new CartItem();
            cartItem.setUserId(userId);
            cartItem.setProductId(product.getId());
            cartItem.setAmount(amount);
            cartItem.setProductPrice(new BigDecimal(product.getPrice().intValue() * amount));
            cartItem = cartItemRepository.save(cartItem);
        }

        productClient.updateProductAmount(token, product.getId(), product.getAmount() - amount);

        return cartItem;
    }

    @Override
    public  List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }


    @Override
    public void clearCartItemByUserId(Long userId) {
        cartItemRepository.deleteByUserId(userId);
    }

    @Override
    public void removeCartItemById(String cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return Cart.builder()
                .id(UUID.randomUUID().toString())
                .userId(userId)
                .cartItems(cartItemRepository.findAllByUserId(userId))
                .build();
    }

    @Override
    public CartItem findCartItemById(String cartItemId) {
        return cartItemRepository.findById(cartItemId).orElseThrow();
    }

    @Override
    public Cart getCartById(String id) {
        return cartRepository.findById(id).orElseThrow();
    }

    @Override
    public void clearCart(Long userId) {
        cartItemRepository.deleteAllByUserId(userId);
    }
}
