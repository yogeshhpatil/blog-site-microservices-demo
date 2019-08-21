package com.blog.application.serviceArticles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceArticlesApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceArticlesApplication.class, args);
	}
}
