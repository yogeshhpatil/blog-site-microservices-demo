package com.blog.application.articleservice.controller;

import com.blog.application.articleservice.exceptiondetails.ArticleNotFoundException;
import com.blog.application.articleservice.model.ArticleList;
import com.blog.application.articleservice.service.ArticleAccessService;
import com.blog.application.articleservice.model.Article;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/v1/articles")
@Api(value = "ArticleController", description = "Provide end point to access article")
public class ArticleController {

    ArticleAccessService articleAccessService;

    public ArticleController(ArticleAccessService articleAccessService) {
        this.articleAccessService = articleAccessService;
    }

    @GetMapping
    @ApiOperation(value = "Get all article", response = ArticleList.class, tags = "Version 1")
    public ResponseEntity<ArticleList> getAllArticle(@RequestParam(defaultValue = "") String userId) {
        ArticleList allArticle;
        if (userId.isEmpty()) {
            allArticle = articleAccessService.getAllArticle();
        } else {
            allArticle = articleAccessService.getAllArticleByUser(userId);
        }

        if(allArticle.getArticles().isEmpty())
            throw new ArticleNotFoundException("No Article Found");

        return ResponseEntity.ok(allArticle);
    }

    @GetMapping("/{articleId}")
    @ApiOperation(value = "Get specific article by articleId", response = Article.class, tags = "Version 1")
    public ResponseEntity<Article> getArticle(@PathVariable(name = "articleId") Integer articleId) {

        Optional<Article> articleById = articleAccessService.getArticleById(articleId);

        if(!articleById.isPresent())
            throw new ArticleNotFoundException("No Article Found");

        return ResponseEntity.ok(articleById.get());
    }

    @PostMapping
    @ApiOperation(value = "Add New Article", tags = "Version 1")
    public ResponseEntity<Object> addArticle(@Valid @RequestBody Article article) {
        Article newArticle = articleAccessService.addArticle(article);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newArticle.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{articleId}")
    @ApiOperation(value = "Delete specific article", tags = "Version 1")
    public ResponseEntity<Object> deleteArticle(@PathVariable(value = "articleId") Integer articleId) {
        articleAccessService.deleteArticleById(articleId);
        return ResponseEntity.noContent().build();
    }
}
