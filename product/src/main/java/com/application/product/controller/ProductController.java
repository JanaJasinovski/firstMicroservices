package com.application.product.controller;

import com.application.product.dto.ProductDto;
import com.application.product.feignClient.UserClient;
import com.application.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping( "/products" )
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final UserClient userClient;

    @PostMapping( "/{username}" )
    public void addProduct(@RequestBody ProductDto productDto, @PathVariable String username) {
        if (userClient.isUserAdmin(username)) {
            productService.createProduct(productDto, username);
        }
    }

    @GetMapping( "/{name}" )
    public ProductDto getProductByName(@PathVariable String name) {
        return productService.findByName(name);
    }

    @GetMapping( "/{startPrice}/{endPrice}" )
    public List<ProductDto> getProductPriceBetween(@PathVariable BigDecimal startPrice, @PathVariable BigDecimal endPrice) {
        return productService.findByPriceBetween(startPrice, endPrice);
    }

    @GetMapping( "/all/{amount}" )
    public List<ProductDto> getProductByAmount(@PathVariable Long amount) {
        return productService.findByAmount(amount);
    }
}
