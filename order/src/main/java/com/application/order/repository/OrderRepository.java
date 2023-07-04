package com.application.order.repository;

import com.application.order.dto.CartItemDto;
import com.application.order.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByUserId(Long userId);
    List<CartItemDto> findCartItemsByIdAndUserId(String orderId, Long userId);

}
