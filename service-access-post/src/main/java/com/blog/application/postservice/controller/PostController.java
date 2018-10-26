package com.blog.application.postservice.controller;

import com.blog.application.postservice.service.PostAccessService;
import com.blog.application.postservice.model.Comment;
import com.blog.application.postservice.model.Post;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/posts")
@Api(value = "PostController", description = "Provide end point to access Posts")
public class PostController {

    PostAccessService postAccessService;

    public PostController(PostAccessService postAccessService) {
        this.postAccessService = postAccessService;
    }

    @GetMapping
    @ApiOperation(value = "Get all posts", response = List.class, tags = "Version 1")
    public List<Post> getAllPost() {
        return postAccessService.getAllPost();
    }

    @GetMapping(params = {"category"})
    @ApiOperation(value = "Get posts by Category", response = List.class, tags = "Version 1")
    public List<Post> getPostByCategory(@RequestParam String category) {
        return postAccessService.getPostByCategory(category);
    }

    @PostMapping
    @ApiOperation(value = "Add New Post", tags = "Version 1")
    public Post addPost(@Valid @RequestBody Post post) {
        return postAccessService.addNewPost(post);
    }

    @GetMapping("/{postId}")
    @ApiOperation(value = "Get specific post by postId", response = Post.class, tags = "Version 1")
    public Optional<Post> getPost(@PathVariable(name = "postId") Integer postId) {
        return postAccessService.getPostById(postId);
    }

    @DeleteMapping("/{postId}")
    @ApiOperation(value = "Delete specific post", tags = "Version 1")
    public void deletePost(@PathVariable(value = "postId") Integer postId) {
        postAccessService.deletePostById(postId);
    }

    @PostMapping("/{postId}/comments")
    @ApiOperation(value = "Comment to post", tags = "Version 1")
    public void commentPost(@PathVariable(value = "postId") Integer postId,
                            @Valid @RequestBody Comment comment) {

        postAccessService.addCommentToPost(postId,comment);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    @ApiOperation(value = "Delete Comment from post", tags = "Version 1")
    public void deleteComment(@PathVariable(value = "postId") Integer postId,
                              @PathVariable(value = "commentId") Integer commentId) {
        postAccessService.deleteCommentFromPost(postId,commentId);
    }

}
