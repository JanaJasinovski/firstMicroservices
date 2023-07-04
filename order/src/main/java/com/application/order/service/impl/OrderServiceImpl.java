package com.application.order.service.impl;

import com.application.order.client.CartItemClient;
import com.application.order.client.ProductClient;
import com.application.order.client.UserClient;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartItemClient cartItemClient;
    private final ProductClient productClient;

    @Override
    public Order createOrder(Long userId, String token) {
        List<CartItemDto> cartItems = cartItemClient.getCartItemByUserId(token);
        Order order = getOrder(token, userId, cartItems);

        order = orderRepository.save(order);

        for (CartItemDto cartItemDto : cartItems) {
            ProductDto product = productClient.getProductById(token, cartItemDto.getProductId());
            if (product.getAmount() > cartItemDto.getAmount()) {
                productClient.updateProductAmount(token, product.getId(), product.getAmount() - cartItemDto.getAmount());
            } else {
                orderRepository.delete(order);
            }
        }

        cartItemClient.clearCartItemsByUserId(token);

        return order;
    }

    private Order getOrder(String token, Long userId, List<CartItemDto> cartItems) {
        return this.createOrder(token, userId, cartItems);
    }

    private Order createOrder(String token, Long userId, List<CartItemDto> cartItems) {
        Long totalAmount = cartItems.stream()
                .mapToLong(CartItemDto::getAmount)
                .sum();

        List<BigDecimal> prices = cartItems.stream()
                .map(CartItemDto::getProductPrice)
                .toList();

        BigDecimal totalPrice = prices.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        return Order.builder()
                .id(UUID.randomUUID().toString())
                .totalAmount(totalAmount)
                .totalPrice(totalPrice)
                .orderStatus("In Progress")
                .dateCreated(dateFormat.format(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())))
                .deliveredDate(dateFormat.format(Date.from(LocalDate.now().plusDays(5).atStartOfDay(ZoneId.systemDefault()).toInstant())))
                .userId(userId)
                .cartItems(cartItems)
                .build();
    }

    @Override
    public void deleteOrderById(String orderId) {
    }

    @Override
    public List<Order> getOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<CartItemDto> getCartItemsFromOrder(Long userId) {
        List<Order> orders = getOrders(userId);
        for (Order order : orders) {
            return order.getCartItems();
        }
        return null;
    }

    @Override
    public List<CartItemDto> getAllCartItems(String orderId, Long userId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null && order.getUserId().equals(userId)) {
            return order.getCartItems().stream()
                    .filter(cartItem -> cartItem.getUserId().equals(userId))
                    .collect(Collectors.toList());
        }
        return null;

    }

}

