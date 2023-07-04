package com.application.product.service;

import com.application.product.dto.ProductDto;
import com.application.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Product createProduct(ProductDto productDTO, Long userId, String token);
    Page<Product> findByCategoryId(Long id, Pageable pageable);
    Product findById(Long id);
    Product getProductByName(String name);
    List<ProductDto> getAll();
    Page<Product> deleteProductIfAdmin(Long productId, Long userId, Pageable pageable);
    void updateProductAmount(Long productId, Long amount);
}
