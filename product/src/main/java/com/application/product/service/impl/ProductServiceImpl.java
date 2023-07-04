package com.application.product.service.impl;

import com.application.product.dto.ProductDto;
import com.application.product.exception.ProductNotFoundException;
import com.application.product.kafka.KafkaProducer;
import com.application.product.model.Product;
import com.application.product.model.ProductCategory;
import com.application.product.repository.ProductCategoryRepository;
import com.application.product.repository.ProductRepository;
import com.application.product.service.ProductService;
import com.application.product.utils.DtoConvert;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final DtoConvert dtoConvert;
    private final KafkaProducer kafkaProducer;

    @PostConstruct
    public void initialize() {
        kafkaProducer.sendAllDataToKafka();
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.findByName(name);
    }
    @Override
    public Product createProduct(ProductDto productDTO, Long userId, String token) {
        String categoryName = productDTO.getCategoryName();
        ProductCategory productCategory = productCategoryRepository.findProductCategoryByCategoryName(categoryName);

        if (productCategory == null) {
            productCategory = new ProductCategory(categoryName);
            productCategoryRepository.save(productCategory);
        }

        Product product = new Product(productDTO.getName(),
                                      productDTO.getPrice(),
                                      productDTO.getAmount(),
                                      productDTO.getPicture(),
                                      userId);
        product.setCategory(productCategory);
        Product savedProduct = productRepository.save(product);
        ProductDto productDto = dtoConvert.convertProduct(savedProduct);
        System.out.println(productDto);
        kafkaProducer.sendProduct(productDto);
        return  savedProduct; //dtoConvert.convertProduct(productRepository.save(product));
    }

    @Override
    public Page<Product> findByCategoryId(Long id, Pageable pageable) {
        ProductCategory productCategory = productCategoryRepository.findById(id).orElseThrow();
        return productRepository.findByCategory(productCategory, pageable);
    }



    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow();
//        return dtoConvert.convertProduct(productRepository.findById(id).orElseThrow(() ->
//                new ProductNotFoundException("Product not found with id: " + id)));
    }


//
//    @Override
//    public List<ProductDto> findByAmount(Long amount) {
//        return productRepository.findByAmount(amount).stream()
//                .map(dtoConvert::convertProduct)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<ProductDto> findProductByNameAndPriceBetween(String name, BigDecimal startPrice, BigDecimal endPrice) {
//        return productRepository.findByNameAndPriceBetween(name, startPrice, endPrice).stream()
//                .map(dtoConvert::convertProduct)
//                .collect(Collectors.toList());
//    }

    @Override
    public List<ProductDto> getAll() {
        return productRepository.findAll().stream().map(dtoConvert::convertProduct).collect(Collectors.toList());
    }


    @Override
    public Page<Product> deleteProductIfAdmin(Long productId, Long userId, Pageable pageable) {
//        Product product = productRepository.findById(productId).orElseThrow();
//        productRepository.delete(product);
        Product product = productRepository.findById(productId).orElseThrow();
        productRepository.delete(product);
        return productRepository.findAll(pageable);
    }

//    @Override
//    public List<ProductDto> getProductByAmountAndPriceBetween(Long amount, BigDecimal startPrice, BigDecimal endPrice) {
//        return productRepository.findProductByAmountAndPriceBetween(amount, startPrice, endPrice).stream()
//                .map(dtoConvert::convertProduct)
//                .collect(Collectors.toList());
//    }

    @Override
    public void updateProductAmount(Long productId, Long amount) {
        Product product = productRepository.findById(productId).orElseThrow();
        if(product.getAmount() > 0) {
            product.setAmount(amount);
        } else if (product.getAmount() < 0){
            productRepository.deleteById(product.getId());
        }
        productRepository.save(product);
    }

}
