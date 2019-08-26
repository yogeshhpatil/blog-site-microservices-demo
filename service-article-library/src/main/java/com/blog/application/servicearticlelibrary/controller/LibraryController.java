package com.blog.application.servicearticlelibrary.controller;

import com.blog.application.servicearticlelibrary.model.Article;
import com.blog.application.servicearticlelibrary.model.ArticlesList;
import com.blog.application.servicearticlelibrary.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/articles")
    public ResponseEntity<ArticlesList> getAllArticle() {
        ArticlesList allArticles = libraryService.getAllArticles();
        return ResponseEntity.ok(allArticles);
    }

    @GetMapping("/articles/{articleId}")
    public ResponseEntity<Article> getArticle(@PathVariable String articleId) {
        return libraryService.getArticle(articleId);
    }
}
