package com.example.elasticSearch.repository;

import com.example.elasticSearch.dto.ProductDto;
import com.example.elasticSearch.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface ProductRepository extends ElasticsearchRepository<Product, Long> {
    @Query( "{\"bool\": {\"must\": [{\"match\": {\"name\": \"?0\"}}]}}" )
    Page<Product> findByName(String name, Pageable pageable);
    Page<Product> findById(Long id, Pageable pageable);
    Product findByName(String name);
    Page<Product> findByPriceBetween(BigDecimal startPrice, BigDecimal endPrice, Pageable pageable);

}
