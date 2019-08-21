package com.blog.application.servicecomments.service;

import com.blog.application.servicecomments.model.Comment;
import com.blog.application.servicecomments.model.CommentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


@Service
public class CommentService {

    @Autowired
    WebClient.Builder webClientBuilder;

    public CommentList getAllComments() {
        List<Comment> block = webClientBuilder.build()
                .method(HttpMethod.GET)
                .uri("https://jsonplaceholder.typicode.com/comments/")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Comment>>() {
                })
                .block();

        CommentList commentList = new CommentList();
        commentList.setComments(block);
        return commentList;
    }

    public Comment getComment(String commentId) {
        Comment comment = webClientBuilder.build()
                .method(HttpMethod.GET)
                .uri("https://jsonplaceholder.typicode.com/comments/" + commentId)
                .retrieve()
                .bodyToMono(Comment.class)
                .block();

        return comment;
    }

    public CommentList getCommentsByPostId(String postId) {
        List<Comment> block = webClientBuilder.build()
                .method(HttpMethod.GET)
                .uri("https://jsonplaceholder.typicode.com/comments?postId=" + postId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Comment>>() {
                })
                .block();

        CommentList commentList = new CommentList();
        commentList.setComments(block);
        return commentList;
    }

    public Comment addComment(Comment comment) {
        Comment created = webClientBuilder.build()
                .method(HttpMethod.POST)
                .uri("https://jsonplaceholder.typicode.com/comments")
                .body(BodyInserters.fromObject(comment))
                .retrieve()
                .bodyToMono(Comment.class).block();

        System.out.println("Created comment :: " + created);

        return created;
    }
}
