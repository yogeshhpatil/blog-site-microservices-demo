package com.blog.application.serviceArticles.daoImpl;

import com.blog.application.serviceArticles.dao.ArticlesDAO;
import com.blog.application.serviceArticles.model.Article;
import com.blog.application.serviceArticles.repository.ArticlesRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Primary
@Qualifier("mysql")
public class ArticlesDAOImpl implements ArticlesDAO {

    private ArticlesRepository articlesRepository;

    public ArticlesDAOImpl(ArticlesRepository articlesRepository) {
        this.articlesRepository = articlesRepository;
    }

    @Override
    public List<Article> getAllArticles() {
        Iterable<Article> allArticles = articlesRepository.findAll();

        List<Article> allArticleList =new ArrayList<>();
        if(allArticles.iterator().hasNext()){
            allArticles.forEach(allArticleList::add);
            return allArticleList;
        }
        return allArticleList;
    }

    @Override
    public List<Article> getAllArticleByUser(String userId) {
        return articlesRepository.findByUserId(userId);
    }

    @Override
    public Optional<Article> getArticleById(Integer articleId) {
        return articlesRepository.findById(articleId);
    }

    @Override
    public Article addNewArticle(Article article) {
        return articlesRepository.save(article);
    }

    @Override
    public boolean deleteArticleById(Integer articleId) {
        articlesRepository.deleteById(articleId);
        return true;
    }
}
