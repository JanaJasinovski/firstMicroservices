package com.application.product.service;

import com.application.product.dto.ProductDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void createProduct(ProductDto productDTO, String username);
    ProductDto findByName(String name);
    List<ProductDto> findByPriceBetween(BigDecimal startPrice, BigDecimal endPrice);
    List<ProductDto> findByAmount(Long amount);
}
