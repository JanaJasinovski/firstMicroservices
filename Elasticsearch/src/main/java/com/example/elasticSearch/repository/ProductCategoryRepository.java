package com.example.elasticSearch.repository;

import com.example.elasticSearch.model.ProductCategory;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends ElasticsearchRepository<ProductCategory, Long> {
}
