package com.blog.application.servicearticlelibrary.service;

import com.blog.application.servicearticlelibrary.model.Article;
import com.blog.application.servicearticlelibrary.model.ArticlesList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ArticlesService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${name.articles-service}")
    private String serviceName;

    public ArticlesList getAllArticles() {
        return webClientBuilder.build()
                .method(HttpMethod.GET)
                .uri("http://" + serviceName + "/v1/articles")
                .retrieve()
                .bodyToMono(ArticlesList.class)
                .block();
    }

    public Article getArticleById(String articleId) {
        return webClientBuilder.build()
                .method(HttpMethod.GET)
                .uri("http://" + serviceName + "/v1/articles/" + articleId)
                .retrieve()
                .bodyToMono(Article.class)
                .block();
    }
}
