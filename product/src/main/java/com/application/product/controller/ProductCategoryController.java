package com.application.product.controller;

import com.application.product.model.ProductCategory;
import com.application.product.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin()
@RestController
@RequestMapping( "/category" )
@RequiredArgsConstructor
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;
    @GetMapping( "/allCategories" )
    public List<ProductCategory> getAll() {
        return productCategoryService.getAllCategories();
    }
}
