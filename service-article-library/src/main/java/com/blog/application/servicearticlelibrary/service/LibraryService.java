package com.blog.application.servicearticlelibrary.service;

import com.blog.application.servicearticlelibrary.model.Article;
import com.blog.application.servicearticlelibrary.model.ArticlesList;
import com.blog.application.servicearticlelibrary.model.CommentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {

    @Autowired
    private ArticlesService articlesService;

    @Autowired
    private CommentsService commentsService;

    public ArticlesList getAllArticles() {

        ArticlesList allArticles = articlesService.getAllArticles();

        ArticlesList commentsForArticles = commentsService.getCommentsForArticles(allArticles);

        return commentsForArticles;
    }

    public ResponseEntity<Article> getArticle(String articleId) {
        Article article = articlesService.getArticleById(articleId);

        CommentList comments = commentsService.getComments(article);

        article.setCommentList(comments);

        return ResponseEntity.ok(article);
    }
}
