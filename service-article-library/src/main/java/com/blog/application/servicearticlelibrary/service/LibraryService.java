package com.blog.application.servicearticlelibrary.service;

import com.blog.application.servicearticlelibrary.model.Article;
import com.blog.application.servicearticlelibrary.model.ArticlesList;
import com.blog.application.servicearticlelibrary.model.CommentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    @Autowired
    WebClient.Builder webClientBuilder;

    public ArticlesList getAllArticles() {
        // getting articles from service-articles
        ArticlesList articlesList = webClientBuilder.build()
                .method(HttpMethod.GET)
                .uri("http://service-articles/v1/articles")
                .retrieve()
                .bodyToMono(ArticlesList.class)
                .block();

        // getting comments for each article from service-comments
        List<Article> articles = articlesList.getArticles()
                .stream()
                .map(article -> {
                    CommentList commentList = webClientBuilder.build()
                            .method(HttpMethod.GET)
                            .uri("http://service-comments/v1/comments?postId=" + article.getId())
                            .retrieve()
                            .bodyToMono(CommentList.class)
                            .block();

                    article.setCommentList(commentList);
                    return article;
                }).collect(Collectors.toList());

        articlesList.setArticles(articles);

        return articlesList;
    }
}
