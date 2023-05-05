package com.application.order.service.impl;

import com.application.order.client.CartItemClient;
import com.application.order.client.ProductClient;
import com.application.order.dto.CartItemDto;
import com.application.order.dto.ProductDto;
import com.application.order.model.Order;
import com.application.order.repository.OrderRepository;
import com.application.order.security.TokenProvider;
import com.application.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartItemClient cartItemClient;
    private final ProductClient productClient;
    private final TokenProvider tokenProvider;

    @Override
    public Order createOrder(Long userId, String token) {
        List<CartItemDto> cartItems = cartItemClient.getCartItemByUserId(token);
        Order order = getOrder(userId, cartItems);
        order = orderRepository.save(order);

        for (CartItemDto cartItemDto : cartItems) {
            ProductDto product = productClient.getProductById(token, cartItemDto.getProductId());
            productClient.updateProductAmount(token, product.getId(), product.getAmount() - cartItemDto.getAmount());
            if (product.getAmount() <= 0) {
                orderRepository.delete(order);
            }
        }

        cartItemClient.clearCartItemByUserId(token);

        return order;
    }

    private Order getOrder(Long userId, List<CartItemDto> cartItems) {
        return this.createOrder(userId, cartItems);
    }

    private Order createOrder(Long userId, List<CartItemDto> cartItems) {
        Long totalAmount = cartItems.stream()
                .mapToLong(CartItemDto::getAmount)
                .sum();

        List<BigDecimal> prices = cartItems.stream()
                .map(CartItemDto::getProductPrice)
                .toList();

        BigDecimal totalPrice = prices.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        return Order.builder()
                .id(UUID.randomUUID().toString())
                .totalAmount(totalAmount)
                .totalPrice(totalPrice)
                .orderStatus("In Progress")
                .dateCreated(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .deliveredDate(Date.from(LocalDate.now().plusDays(5).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .userId(userId)
                .cartItems(cartItems)
                .build();
    }

    @Override
    public void deleteOrderById(String orderId) {
    }

    @Override
    public Order getOrder(Long userId) {
        return orderRepository.findOrderByUserId(userId);
    }

}

