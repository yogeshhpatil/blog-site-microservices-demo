package com.blog.application.serviceArticles.service;

import com.blog.application.serviceArticles.dao.ArticlesDAO;
import com.blog.application.serviceArticles.model.Article;
import com.blog.application.serviceArticles.model.ArticlesList;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ArticlesService {

    ArticlesDAO articlesDAO;

    public ArticlesService(ArticlesDAO articlesDAO) {
        this.articlesDAO = articlesDAO;
    }

    public ArticlesList getAllArticles() {
        ArticlesList articlesList = new ArticlesList();
        articlesList.setArticles(articlesDAO.getAllArticles());
        return articlesList;
    }

    public ArticlesList getAllArticlesByUser(String userId) {
        ArticlesList articlesList = new ArticlesList();
        articlesList.setArticles(articlesDAO.getAllArticleByUser(userId));
        return articlesList;
    }

    public Optional<Article> getArticleById(Integer articleId) {
        return articlesDAO.getArticleById(articleId);
    }

    public Article addArticle(Article article) {
        article.setPostingDate(new Date());
        return articlesDAO.addNewArticle(article);
    }

    public void deleteArticleById(Integer articleId) {
        articlesDAO.deleteArticleById(articleId);
    }
}
