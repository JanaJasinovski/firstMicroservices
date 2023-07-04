package com.application.order.client;

import com.application.order.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient( name = "product", url = "http://localhost:8082", path = "products" )
public interface ProductClient {

    @PutMapping( "/product/update/{productId}/{amount}" )
    void updateProductAmount(
            @RequestHeader( HttpHeaders.AUTHORIZATION ) String token,
            @PathVariable Long productId,
            @PathVariable Long amount);

    @GetMapping( "/product/id/{id}" )
    ProductDto getProductById(@RequestHeader( HttpHeaders.AUTHORIZATION ) String token, @PathVariable Long id);
}
