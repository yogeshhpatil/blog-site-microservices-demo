package com.blog.application.serviceArticles.controller;

import com.blog.application.serviceArticles.exceptiondetails.ArticleNotFoundException;
import com.blog.application.serviceArticles.model.ArticlesList;
import com.blog.application.serviceArticles.service.ArticlesService;
import com.blog.application.serviceArticles.model.Article;
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
@Api(value = "ArticlesController", description = "Provide end point to access articles")
public class ArticlesController {

    ArticlesService articlesService;

    public ArticlesController(ArticlesService articlesService) {
        this.articlesService = articlesService;
    }

    @GetMapping
    @ApiOperation(value = "Get all articles", response = ArticlesList.class, tags = "Version 1")
    public ResponseEntity<ArticlesList> getAllArticle(@RequestParam(defaultValue = "") String userId) {
        ArticlesList allArticle;
        if (userId.isEmpty()) {
            allArticle = articlesService.getAllArticles();
        } else {
            allArticle = articlesService.getAllArticlesByUser(userId);
        }

        if(allArticle.getArticles().isEmpty())
            throw new ArticleNotFoundException("No Article Found");

        return ResponseEntity.ok(allArticle);
    }

    @GetMapping("/{articleId}")
    @ApiOperation(value = "Get specific article by articleId", response = Article.class, tags = "Version 1")
    public ResponseEntity<Article> getArticle(@PathVariable(name = "articleId") Integer articleId) {

        Optional<Article> articleById = articlesService.getArticleById(articleId);

        if(!articleById.isPresent())
            throw new ArticleNotFoundException("No Article Found");

        return ResponseEntity.ok(articleById.get());
    }

    @PostMapping
    @ApiOperation(value = "Add New Article", tags = "Version 1")
    public ResponseEntity<Object> addArticle(@Valid @RequestBody Article article) {
        Article newArticle = articlesService.addArticle(article);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newArticle.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{articleId}")
    @ApiOperation(value = "Delete specific article", tags = "Version 1")
    public ResponseEntity<Object> deleteArticle(@PathVariable(value = "articleId") Integer articleId) {
        articlesService.deleteArticleById(articleId);
        return ResponseEntity.noContent().build();
    }
}
