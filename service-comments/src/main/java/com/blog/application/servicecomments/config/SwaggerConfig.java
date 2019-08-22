package com.blog.application.servicecomments.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.blog.application"))
//                    .apis(RequestHandlerSelectors.any())
//                    .paths(PathSelectors.ant("/v1/*"))
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Comments Access REST API",
                "API expose some REST end points to access comments.",
                "V 1.0",
                "Terms of service",
                new Contact("Yogesh Patil", "http://www.github.com/yogeshhpatil", "yogesh.hpatil4@gmail.com"),
                "License of API", "http://www.github.com/yogeshhpatil", Collections.emptyList());
    }
}

