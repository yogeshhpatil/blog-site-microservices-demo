package com.blog.application.postservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build()
//                .getApiInfo(getApiInfo());
//    }

    @Bean
    public Docket api() {
        //Register the controllers to swagger
        //Also it is configuring the Swagger Docket
        return new Docket(DocumentationType.SWAGGER_2)//.select()
//                 .apis(RequestHandlerSelectors.any())
//                .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
//                .apis(RequestHandlerSelectors.basePackage("com.blog.application.postservice"))
//                 .paths(PathSelectors.any())
//                 .paths(PathSelectors.ant("/swagger2-demo"))
//                .build()
                .consumes(new HashSet<String>(Arrays.asList("application/json","application/xml")))
                .produces(new HashSet<String>(Arrays.asList("application/json","application/xml")))
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Blog Access REST API",
                "API expose some REST end points to access blog posts.",
                "V 1.0",
                "Terms of service",
                new Contact("Yogesh Patil", "http://www.github.com/yogeshhpatil", "yogesh.hpatil4@gmail.com"),
                "License of API", "http://www.github.com/yogeshhpatil");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //enabling swagger-ui part for visual documentation
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
