package com.example.elasticSearch.config;

import com.example.elasticSearch.dto.ProductDto;
import com.example.elasticSearch.kafka.ProductDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class ConsumerKafkaConfig {

    @Value("${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ProductDto> kafkaListenerContainerFactory(ConsumerFactory<String, ProductDto> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, ProductDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, ProductDto> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "consuming");

//        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
//        Map<String, Class<?>> classMap = new HashMap<>();
//        classMap.put("com.application.Elasticsearch.dto.ProductDto.java", ProductDto.class);
//        typeMapper.setIdClassMapping(classMap);
//        typeMapper.addTrustedPackages("*");

//        jsonDeserializer.addTrustedPackages("com.application.Elasticsearch.dto");

        return new DefaultKafkaConsumerFactory<>(
                config,
                new StringDeserializer(),
                new ProductDeserializer());
    }
}