package com.example.elasticSearch.service;

//import com.example.elasticSearch.dto.ProductDto;
import com.example.elasticSearch.dto.ProductDto;
import com.example.elasticSearch.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.math.BigDecimal;

public interface ProductService {

    void save(ProductDto product) throws IOException;
    Page<Product> findByName(String name, Pageable pageable);
    Product getProductByName(String name);

    Product getProductById(Long id);
    Page<Product> findByPriceBetween(BigDecimal startPrice, BigDecimal endPrice, Pageable pageable);

    void updateProductDocument(ProductDto product);
}
