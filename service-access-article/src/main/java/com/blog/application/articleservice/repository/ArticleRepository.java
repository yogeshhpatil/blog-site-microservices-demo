package com.blog.application.articleservice.repository;

import com.blog.application.articleservice.model.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends CrudRepository<Article,Integer> {
    List<Article> findByUserId(String userId);
}
