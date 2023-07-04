package com.application.order.service;

import com.application.order.dto.CartItemDto;
import com.application.order.model.Order;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface OrderService {
    Order createOrder(Long userId, String token);
    void deleteOrderById(String orderId);

    List<Order> getOrders(Long userId);

    List<CartItemDto> getCartItemsFromOrder(Long userId);

    List<CartItemDto> getAllCartItems(String orderId, Long userId);


}
