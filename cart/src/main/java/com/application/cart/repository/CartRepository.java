package com.application.cart.repository;

import com.application.cart.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {
    void deleteCartByUserId(Long userId);

    List<Cart> findCartByProductId(Long id);

    List<Cart> findCartByUserId(Long userId);

    void deleteAllByUserId(Long userId);
}
