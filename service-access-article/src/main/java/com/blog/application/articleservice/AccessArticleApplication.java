package com.blog.application.articleservice;

import com.blog.application.articleservice.service.ArticleAccessService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AccessArticleApplication {

	ArticleAccessService articleAccessService;

	public AccessArticleApplication(ArticleAccessService articleAccessService) {
		this.articleAccessService = articleAccessService;
	}

	public static void main(String[] args) {
		SpringApplication.run(AccessArticleApplication.class, args);
	}
}
