package com.blog.application.servicearticlelibrary.service;

import com.blog.application.servicearticlelibrary.model.ArticlesList;
import org.springframework.beans.factory.annotation.Autowired;
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
}
