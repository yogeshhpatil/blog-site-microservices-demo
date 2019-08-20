package com.blog.application.serviceArticles.dao;

import com.blog.application.serviceArticles.model.Article;

import java.util.List;
import java.util.Optional;

public interface ArticlesDAO {
    List<Article> getAllArticles();

    List<Article> getAllArticleByUser(String userId);

    Optional<Article> getArticleById(Integer articleId);

    Article addNewArticle(Article article);

    boolean deleteArticleById(Integer articleId);
}
