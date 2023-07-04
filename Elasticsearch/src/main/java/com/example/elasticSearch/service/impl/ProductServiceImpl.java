package com.example.elasticSearch.service.impl;

//import com.example.elasticSearch.dto.ProductDto;
import com.example.elasticSearch.dto.ProductDto;
import com.example.elasticSearch.model.Product;
import com.example.elasticSearch.repository.ProductRepository;
import com.example.elasticSearch.service.ProductService;
import com.example.elasticSearch.utils.DtoConvert;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;

import java.io.IOException;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ElasticsearchOperations elasticsearchOperations;
    private final ProductRepository productRepository;
    private final RestHighLevelClient elasticsearchClient;
    private final DtoConvert dtoConvert;
    @Override
    @KafkaListener( topics = "${kafka.topic.name}", groupId = "${kafka.topic.group_id}", containerFactory = "kafkaListenerContainerFactory" )
    public void save(ProductDto product) {
        System.out.println(product);
        productRepository.save(dtoConvert.convertProductDto(product));
    }

    @Override
    public Page<Product> findByName(String name, Pageable pageable) {
        return productRepository.findByName(name, pageable);
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.findByName(name);
    }
    @Override
    public Product getProductById(Long id) {
        return  productRepository.findById(id).orElseThrow(); //dtoConvert.convertProduct(productRepository.findById(id).orElseThrow());
    }

    @Override
    public Page<Product> findByPriceBetween(BigDecimal startPrice, BigDecimal endPrice, Pageable pageable) {
        return productRepository.findByPriceBetween(startPrice, endPrice, pageable); //.stream().map(dtoConvert::convertProduct).collect(Collectors.toList());
    }

    @Override
    public void updateProductDocument(ProductDto product) {
        IndexRequest indexRequest = new IndexRequest("products")
                .id(product.getId().toString())
                .source("name", product.getName(), "price", product.getPrice().toString(), "amount", product.getAmount().toString(),
                        "picture", product.getPicture(), "categoryName", product.getCategoryName(), "userId", product.getUserId().toString());



        UpdateRequest updateRequest = new UpdateRequest("products", product.getId().toString())
                .doc("name", product.getName(), "price", product.getPrice().toString(), "amount", product.getAmount().toString(),
                        "picture", product.getPicture(), "categoryName", product.getCategoryName(), "userId", product.getUserId().toString());
        try {
            IndexResponse indexResponse = elasticsearchClient.index(indexRequest, RequestOptions.DEFAULT);
            if (indexResponse.getResult() == DocWriteResponse.Result.CREATED || indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
                elasticsearchClient.update(updateRequest, RequestOptions.DEFAULT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
