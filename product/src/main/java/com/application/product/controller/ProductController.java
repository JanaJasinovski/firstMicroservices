package com.application.product.controller;

import com.application.product.dto.ProductDto;
import com.application.product.kafka.KafkaProducer;
import com.application.product.model.Product;
import com.application.product.security.TokenProvider;
import com.application.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping( "/products" )
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final TokenProvider tokenProvider;
    private final KafkaProducer kafkaProducer;

    @GetMapping("/moveDataToKafka")
    public void moveDataToKafka() {
        List<ProductDto> products = productService.getAll();
        kafkaProducer.sendData(products);
    }

    @GetMapping( "/search" )
    public Product getProductByName(@RequestParam( "name" ) String name) {
        return productService.getProductByName(name);
    }

    @PostMapping( "/create" )
    public Product addProduct(@RequestBody ProductDto productDto, HttpServletRequest request, Pageable pageable) {
        String token = "Bearer " + tokenProvider.getToken(request);
        return productService.createProduct(productDto, tokenProvider.extractUser(request).getId(), token);
    }

    @GetMapping( "/all" )
    public List<ProductDto> getAll() {
        return productService.getAll();
    }

    @GetMapping( "/product/id/{id}" )
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping( "/search/findByCategoryId" )
    public Page<Product> getByCategoryId(@Param( "id" ) Long id, Pageable pageable) {
        return productService.findByCategoryId(id, pageable);
    }

    @DeleteMapping( "/delete/product/userId" )
    public Page<Product> deleteProduct(@Param( "productId" ) Long productId, HttpServletRequest request, Pageable pageable) {
        return  productService.deleteProductIfAdmin(productId, tokenProvider.extractUser(request).getId(), pageable);
    }

    @PutMapping( "/product/update/{productId}/{amount}" )
    public void updateProductAmount(@PathVariable Long productId, @PathVariable Long amount) {
        productService.updateProductAmount(productId, amount);
    }
}
