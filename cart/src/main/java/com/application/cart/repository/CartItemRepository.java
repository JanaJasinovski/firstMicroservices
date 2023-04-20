package com.application.cart.repository;

import com.application.cart.model.CartItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends MongoRepository<CartItem, String> {
    List<CartItem> findAllByUserId(Long userId);
    void deleteByUserId(Long userId);
    void deleteAllByUserId(Long userId);
}
