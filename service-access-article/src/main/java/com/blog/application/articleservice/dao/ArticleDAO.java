package com.blog.application.articleservice.dao;

import com.blog.application.articleservice.model.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleDAO {
    List<Article> getAllArticle();

    List<Article> getAllArticleByUser(String userId);

    Optional<Article> getArticleById(Integer articleId);

    Article addNewArticle(Article article);

    boolean deleteArticleById(Integer articleId);
}
