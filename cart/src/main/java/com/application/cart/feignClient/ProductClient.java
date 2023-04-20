package com.application.cart.feignClient;

import com.application.cart.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;


//product
@FeignClient( name = "product", url = "http://localhost:8082", path = "products")
public interface ProductClient {

    @GetMapping( "/{name}" )
    ProductDto getProductByName(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable String name);

    @PutMapping("/product/update/{productId}/{amount}")
    void updateProductAmount(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                             @PathVariable Long productId,
                             @PathVariable Long amount);
}
