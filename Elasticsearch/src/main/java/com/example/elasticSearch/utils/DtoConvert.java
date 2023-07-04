package com.example.elasticSearch.utils;

import com.example.elasticSearch.dto.ProductDto;
import com.example.elasticSearch.model.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DtoConvert {
    private final ModelMapper modelMapper;

    public ProductDto convertProduct(Product product) {

        return modelMapper.map(product, ProductDto.class);
    }

    public Product convertProductDto(ProductDto product) {

        return modelMapper.map(product, Product.class);
    }
}
