package com.example.elasticSearch.kafka;

//import com.example.elasticSearch.dto.ProductDto;
import com.example.elasticSearch.dto.ProductDto;
import com.example.elasticSearch.model.Product;
import com.example.elasticSearch.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ProductListener {

    private final ProductService elasticsearchService;

    @Autowired
    public ProductListener(ProductService elasticsearchService) {
        this.elasticsearchService = elasticsearchService;
    }

    @KafkaListener(topics = "product-topic")
    public void consumeProduct(ProductDto product) {
        System.out.println(product);
        elasticsearchService.updateProductDocument(product);
    }
}
