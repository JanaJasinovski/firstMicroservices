package com.application.cart.feignClient;

import com.application.cart.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

//product
@FeignClient( name = "product", url = "http://localhost:8082", path = "products")
public interface ProductClient {

//    @GetMapping( "/search/findByNameContaining" )
//    Page<ProductDto> getProductByName(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
//                                      @RequestParam("name") String name,
//                                      Pageable pageable);

    @GetMapping( "/search" )
    ProductDto getProductByName(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestParam("name") String name);

}
