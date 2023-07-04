package com.example.elasticSearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories
@EnableFeignClients
public class ElasticSearchApplication {
	public static void main(String[] args) {
		SpringApplication.run(ElasticSearchApplication.class, args);
	}

}
