package com.application.commentar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableNeo4jRepositories
public class CommentarApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommentarApplication.class, args);
	}

}
