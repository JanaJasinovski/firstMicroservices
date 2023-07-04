package com.example.elasticSearch.kafka;

//import com.example.elasticSearch.dto.ProductDto;
import com.example.elasticSearch.dto.ProductDto;
import com.example.elasticSearch.model.Product;
import com.example.elasticSearch.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProductKafkaListener {

    @Autowired
    private ProductService elasticsearchService;

    @KafkaListener(topics = "${kafka.topicName}", groupId = "elasticsearch-consumer")
    public void consume(ProductDto product) throws IOException {
        elasticsearchService.save(product);
    }
}
