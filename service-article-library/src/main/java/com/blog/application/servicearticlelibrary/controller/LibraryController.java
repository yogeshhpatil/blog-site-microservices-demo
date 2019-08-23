package com.blog.application.servicearticlelibrary.controller;

import com.blog.application.servicearticlelibrary.model.ArticlesList;
import com.blog.application.servicearticlelibrary.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    LibraryService libraryService;

    @GetMapping("/articles")
    public ResponseEntity<ArticlesList> getAllArticle() {
        ArticlesList allArticles = libraryService.getAllArticles();
        return ResponseEntity.ok(allArticles);
    }
}
