package com.example.elasticSearch.feignClient;

//import com.example.elasticSearch.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//product
@FeignClient( name = "product", url = "http://localhost:8082", path = "products" )
public interface ProductClient {

//    @GetMapping( "/search/findByNameContaining" )
//    Page<ProductDto> getProductByName(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
//                                      @RequestParam("name") String name,
//                                      Pageable pageable);

//    @GetMapping( "/all" )
//    List<ProductDto> getAll();

}
