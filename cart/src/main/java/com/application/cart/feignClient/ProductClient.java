package com.application.cart.feignClient;

import com.application.cart.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient( name = "product", url = "http://localhost:8082", path = "products")
public interface ProductClient {

    @GetMapping( "/{name}" )
    ProductDto getProductByName(@PathVariable String name);
}
