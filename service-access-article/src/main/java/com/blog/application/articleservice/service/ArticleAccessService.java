package com.blog.application.articleservice.service;

import com.blog.application.articleservice.dao.ArticleDAO;
import com.blog.application.articleservice.model.Article;
import com.blog.application.articleservice.model.ArticleList;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ArticleAccessService {

    ArticleDAO articleDAO;

    public ArticleAccessService(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    public ArticleList getAllArticle() {
        ArticleList articleList = new ArticleList();
        articleList.setArticles(articleDAO.getAllArticle());
        return articleList;
    }

    public ArticleList getAllArticleByUser(String userId) {
        ArticleList articleList = new ArticleList();
        articleList.setArticles(articleDAO.getAllArticleByUser(userId));
        return articleList;
    }

    public Optional<Article> getArticleById(Integer articleId) {
        return articleDAO.getArticleById(articleId);
    }

    public Article addArticle(Article article) {
        article.setPostingDate(new Date());
        return articleDAO.addNewArticle(article);
    }

    public void deleteArticleById(Integer articleId) {
        articleDAO.deleteArticleById(articleId);
    }
}
