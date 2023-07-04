package com.example.elasticSearch.kafka;

import com.example.elasticSearch.dto.ProductDto;
import com.example.elasticSearch.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class ProductDeserializer implements Deserializer<ProductDto> {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public ProductDto deserialize(String s, byte[] bytes) {
        try {
            if (bytes == null){
                System.out.println("Null received at deserializing");
                return null;
            }
            return objectMapper.readValue(new String(bytes, "UTF-8"), ProductDto.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to MessageDto");
        }
    }

    @Override
    public void close() {
    }
}
