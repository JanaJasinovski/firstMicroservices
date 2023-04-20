package com.application.product.controller;

import com.application.product.dto.ProductDto;
import com.application.product.security.TokenProvider;
import com.application.product.service.ProductService;
import com.application.product.utils.DtoConvert;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping( "/products" )
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final TokenProvider tokenProvider;

    @PostMapping( "/create" )
    public void addProduct(@RequestBody ProductDto productDto, HttpServletRequest request) {
        productService.createProduct(productDto, tokenProvider.extractUser(request).getId());
    }

    @GetMapping( "/all" )
    public List<ProductDto> getAll() {
        return productService.getAll();
    }

    @GetMapping( "/{name}" )
    public ProductDto getProductByName(@PathVariable String name) {
        return productService.findByName(name);
    }

    @GetMapping( "/{name}/{startPrice}/{endPrice}" )
    public List<ProductDto> getProductByNameAndPriceBetween(@PathVariable String name, @PathVariable BigDecimal startPrice, @PathVariable BigDecimal
            endPrice) {
        return productService.findProductByNameAndPriceBetween(name, startPrice, endPrice);
    }

    @GetMapping( "/{startPrice}/{endPrice}" )
    public List<ProductDto> getProductPriceBetween(@PathVariable BigDecimal startPrice, @PathVariable BigDecimal
            endPrice) {
        return productService.findByPriceBetween(startPrice, endPrice);
    }

    @GetMapping( "/all/{amount}/{startPrice}/{endPrice}" )
    public List<ProductDto> getProductByAmount(@PathVariable Long amount, @PathVariable BigDecimal startPrice, @PathVariable BigDecimal
            endPrice) {
        return productService.getProductByAmountAndPriceBetween(amount, startPrice, endPrice);
    }

    @GetMapping( "/all/{amount}" )
    public List<ProductDto> getProductByAmountAndPriceBetween(@PathVariable Long amount, @PathVariable BigDecimal startPrice, @PathVariable BigDecimal
            endPrice) {
        return productService.findByAmount(amount);
    }

    @PutMapping("/product/update/{productId}/{amount}")
    public void updateProductAmount(@PathVariable Long productId, @PathVariable Long amount) {
        productService.updateProductAmount(productId, amount);
    }
}
