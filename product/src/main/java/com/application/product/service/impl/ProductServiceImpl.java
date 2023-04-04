package com.application.product.service.impl;

import com.application.product.dto.ProductDto;
import com.application.product.exception.ProductNotFoundException;
import com.application.product.model.Product;
import com.application.product.repository.ProductRepository;
import com.application.product.service.ProductService;
import com.application.product.utils.DtoConvert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final DtoConvert dtoConvert;

    @Override
    public void createProduct(ProductDto productDTO, String username) {
        Product product = new Product(productDTO.getName(), productDTO.getPrice(), productDTO.getAmount(), username);
        productRepository.save(product);
    }

    @Override
    public ProductDto findByName(String name) {
        return dtoConvert.convertProduct(productRepository.findByName(name).orElseThrow(() ->
                new ProductNotFoundException("Product not found with name: " + name)));
    }

    @Override
    public List<ProductDto> findByPriceBetween(BigDecimal startPrice, BigDecimal endPrice) {
        return productRepository.findByPriceBetween(startPrice, endPrice).stream().map(dtoConvert::convertProduct).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findByAmount(Long amount) {
        return productRepository.findByAmount(amount).stream().map(dtoConvert::convertProduct).collect(Collectors.toList());
    }
}
