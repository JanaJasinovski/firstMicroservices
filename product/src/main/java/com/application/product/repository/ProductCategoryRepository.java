package com.application.product.repository;

import com.application.product.model.ProductCategory;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    ProductCategory findProductCategoryByCategoryName(String name);
    ProductCategory findProductCategoryById(Long id);
}
