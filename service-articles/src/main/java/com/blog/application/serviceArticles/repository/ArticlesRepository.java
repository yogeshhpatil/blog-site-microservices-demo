package com.blog.application.serviceArticles.repository;

import com.blog.application.serviceArticles.model.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticlesRepository extends CrudRepository<Article,Integer> {
    List<Article> findByUserId(String userId);
}
