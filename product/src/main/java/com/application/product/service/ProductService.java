package com.application.product.service;

import com.application.product.dto.ProductDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void createProduct(ProductDto productDTO, Long userId);
    ProductDto findByName(String name);
    ProductDto findById(Long id);
    List<ProductDto> findByPriceBetween(BigDecimal startPrice, BigDecimal endPrice);
    List<ProductDto> findByAmount(Long amount);
    List<ProductDto> findProductByNameAndPriceBetween(String name, BigDecimal startPrice, BigDecimal endPrice);
    List<ProductDto> getAll();
    List<ProductDto> getProductByAmountAndPriceBetween(Long amount, BigDecimal startPrice, BigDecimal endPrice);
    void updateProductAmount(Long productId, Long amount);
}
