package com.application.product.repository;

import com.application.product.model.Product;
import com.application.product.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategory(ProductCategory category, Pageable pageable);
    Product findByName(String name);

    Product deleteByIdAndUserId(Long id, Long userId);
//    List<Product> findByAmount(Long amount);
//    List<Product> findByNameAndPriceBetween(String name, BigDecimal startPrice, BigDecimal endPrice);
//    List<Product> findProductByAmountAndPriceBetween(Long amount, BigDecimal startPrice, BigDecimal endPrice);
}
