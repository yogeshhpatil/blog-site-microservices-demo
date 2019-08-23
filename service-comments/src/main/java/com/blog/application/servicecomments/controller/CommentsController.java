package com.blog.application.servicecomments.controller;

import com.blog.application.servicecomments.model.Comment;
import com.blog.application.servicecomments.model.CommentList;
import com.blog.application.servicecomments.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1/comments")
public class CommentsController {

    @Autowired
    CommentService commentService;

    @GetMapping()
    public ResponseEntity<CommentList> getComments(@RequestParam(defaultValue = "") String postId) {
        CommentList commentList;
        if (postId.isEmpty())
            commentList = commentService.getAllComments();
        else
            commentList = commentService.getCommentsByPostId(postId);

        return ResponseEntity.ok(commentList);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> getComment(@PathVariable(required = true) String commentId) {
        Comment comment = commentService.getComment(commentId);
        return ResponseEntity.ok(comment);
    }

    @PostMapping
    public ResponseEntity<Object> addComment(@RequestBody Comment comment) {

        Comment comment1 = commentService.addComment(comment);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(comment1.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}
