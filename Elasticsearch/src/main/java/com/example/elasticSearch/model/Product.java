package com.example.elasticSearch.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;

@Document(indexName = "products", createIndex = false)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Product {

    @Id
    private Long id;
    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Double, name = "price")
    private BigDecimal price;

    @Field(type = FieldType.Long, name = "amount")
    private Long amount;

    @Field(type = FieldType.Text, name = "picture")
    private String picture;
    @Field(type = FieldType.Keyword, name = "category")
    @JsonIgnore
    private ProductCategory category;
    @Field(type = FieldType.Long, name = "userId")
    private Long userId;

}
