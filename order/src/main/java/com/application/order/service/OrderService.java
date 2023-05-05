package com.application.order.service;

import com.application.order.model.Order;

public interface OrderService {
    Order createOrder(Long userId, String token);
    void deleteOrderById(String orderId);

    Order getOrder(Long userId);
}
