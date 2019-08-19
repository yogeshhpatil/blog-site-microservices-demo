package com.blog.application.postservice;

import com.blog.application.postservice.service.PostAccessService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AccessPostApplication {

	PostAccessService postAccessService;

	public AccessPostApplication(PostAccessService postAccessService) {
		this.postAccessService = postAccessService;
	}

	public static void main(String[] args) {
		SpringApplication.run(AccessPostApplication.class, args);
	}
}
