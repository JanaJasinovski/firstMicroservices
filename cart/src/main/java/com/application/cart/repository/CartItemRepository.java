package com.application.cart.repository;

import com.application.cart.model.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends CrudRepository<CartItem, String> {
    List<CartItem> findAllByUserId(Long userId);

    CartItem findCartItemByProductName(String name);
    void deleteByUserId(Long userId);
    void deleteAllByUserId(Long userId);
    CartItem findCartItemByProductIdAndUserId(Long productId, Long userId);
}
