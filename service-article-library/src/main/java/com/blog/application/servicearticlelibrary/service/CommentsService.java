package com.blog.application.servicearticlelibrary.service;

import com.blog.application.servicearticlelibrary.model.Article;
import com.blog.application.servicearticlelibrary.model.ArticlesList;
import com.blog.application.servicearticlelibrary.model.CommentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentsService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${name.comments-service}")
    private String serviceName;

    public ArticlesList getCommentsForArticles(ArticlesList articlesList) {
        List<Article> articles = articlesList.getArticles()
                .stream()
                .map(article -> {
                    CommentList commentList = getComments(article);

                    article.setCommentList(commentList);
                    return article;
                }).collect(Collectors.toList());

        articlesList.setArticles(articles);

        return articlesList;
    }

    public CommentList getComments(Article article) {
        return webClientBuilder.build()
                .method(HttpMethod.GET)
                .uri("http://" + serviceName + "/v1/comments?postId=" + article.getId())
                .retrieve()
                .bodyToMono(CommentList.class)
                .block();
    }

}
