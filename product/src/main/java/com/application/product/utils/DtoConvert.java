package com.application.product.utils;

import com.application.product.dto.ProductDto;
import com.application.product.model.Product;
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
}
