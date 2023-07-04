package com.example.elasticSearch.controller;

//import com.example.elasticSearch.dto.ProductDto;
import com.example.elasticSearch.model.Product;
import com.example.elasticSearch.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@CrossOrigin()
@RestController
@RequestMapping( "/searches" )
@RequiredArgsConstructor
public class ElasticSearchController {

    private final ProductService productService;

    @GetMapping( "/search/findByNameContaining" )
    public Page<Product> getProductByName(@RequestParam( "name" ) String name, Pageable pageable) {
        return productService.findByName(name, pageable);
    }

    @GetMapping( "/search" )
    public Product getProductByName(@RequestParam( "name" ) String name) {
        return productService.getProductByName(name);
    }

    @GetMapping( "priceBetween" )
    public Page<Product> getProductPriceBetween(@RequestParam("startPrice") BigDecimal startPrice,
                                                @RequestParam("endPrice") BigDecimal endPrice,
                                                Pageable pageable) {
        return productService.findByPriceBetween(startPrice, endPrice, pageable);
    }
}
