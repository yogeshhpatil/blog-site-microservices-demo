package com.blog.application.articleservice.daoImpl;

import com.blog.application.articleservice.dao.ArticleDAO;
import com.blog.application.articleservice.model.Article;
import com.blog.application.articleservice.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Primary
@Qualifier("mysql")
public class ArticleDAOImpl implements ArticleDAO {

    private ArticleRepository articleRepository;

    public ArticleDAOImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> getAllArticle() {
        Iterable<Article> allArticles = articleRepository.findAll();

        List<Article> allArticleList =new ArrayList<>();
        if(allArticles.iterator().hasNext()){
            allArticles.forEach(allArticleList::add);
            return allArticleList;
        }
        return allArticleList;
    }

    @Override
    public List<Article> getAllArticleByUser(String userId) {
        return articleRepository.findByUserId(userId);
    }

    @Override
    public Optional<Article> getArticleById(Integer articleId) {
        return articleRepository.findById(articleId);
    }

    @Override
    public Article addNewArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public boolean deleteArticleById(Integer articleId) {
        articleRepository.deleteById(articleId);
        return true;
    }
}
