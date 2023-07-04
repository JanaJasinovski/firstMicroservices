package com.application.product.kafka;

import com.application.product.dto.ProductDto;
import com.application.product.repository.ProductRepository;
import com.application.product.service.ProductService;
import com.application.product.utils.DtoConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class KafkaProducer {

    private static final String TOPIC_NAME = "product-topic";
    private final ProductRepository productRepository;
    private final DtoConvert dtoConvert;
    private final KafkaTemplate<String, ProductDto> kafkaTemplate;

    @Autowired
    public KafkaProducer(DtoConvert dtoConvert, ProductRepository productRepository, KafkaTemplate<String, ProductDto> kafkaTemplate) {
        this.productRepository = productRepository;
        this.dtoConvert = dtoConvert;
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendAllDataToKafka() {
        List<ProductDto> products = productRepository.findAll().stream().map(dtoConvert::convertProduct).toList();
        for (ProductDto product : products) {
            kafkaTemplate.send(TOPIC_NAME, product.getId().toString(), product);
        }
    }

    public void sendData(List<ProductDto> products) {
        for (ProductDto product : products) {
            kafkaTemplate.send(TOPIC_NAME, product.getId().toString(), product);
        }
    }

    public void sendProduct(ProductDto product) {
        kafkaTemplate.send(TOPIC_NAME, product);
    }
}
