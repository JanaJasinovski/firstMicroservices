package com.application.product.controller;

import com.application.product.dto.ProductDto;
import com.application.product.security.TokenProvider;
import com.application.product.service.ProductService;
import com.application.product.utils.DtoConvert;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
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

    @Value( "${app.jwt.secret}" )
    private String JWT_SECRET;
    private final ProductService productService;
    private final TokenProvider tokenProvider;

    @PostMapping( "/create" )
    @PreAuthorize( "hasAuthority('"++"')" )
    public void addProduct(@RequestBody ProductDto productDto, HttpServletRequest request) {
        productService.createProduct(productDto, tokenProvider.extractUserId(request));
    }

    @GetMapping( "/all" )
    @PreAuthorize( "hasAuthority('ADMIN')" )
    public List<ProductDto> getAll() {
        return productService.getAll();
    }

    @GetMapping( "/{name}" )
    public ProductDto getProductByName(@PathVariable String name) {
        return productService.findByName(name);
    }

    @GetMapping( "/{startPrice}/{endPrice}" )
    public List<ProductDto> getProductPriceBetween(@PathVariable BigDecimal startPrice, @PathVariable BigDecimal
            endPrice) {
        return productService.findByPriceBetween(startPrice, endPrice);
    }

    @GetMapping( "/all/{amount}" )
    public List<ProductDto> getProductByAmount(@PathVariable Long amount) {
        return productService.findByAmount(amount);
    }
}
