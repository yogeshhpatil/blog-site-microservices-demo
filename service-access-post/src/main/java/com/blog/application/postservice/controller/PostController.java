package com.blog.application.postservice.controller;

import com.blog.application.postservice.exceptiondetails.PostNotFoundException;
import com.blog.application.postservice.model.PostList;
import com.blog.application.postservice.service.PostAccessService;
import com.blog.application.postservice.model.Post;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import javax.validation.Valid;
import java.net.URI;
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
    @ApiOperation(value = "Get all posts", response = PostList.class, tags = "Version 1")
    public ResponseEntity<PostList> getAllPost() {
        List<Post> allPost = postAccessService.getAllPost();

        if(allPost.isEmpty())
            throw new PostNotFoundException("No Post Found");

        PostList postList = new PostList();
        postList.setPosts(allPost);

        return ResponseEntity.ok(postList);
    }

    @GetMapping("/{postId}")
    @ApiOperation(value = "Get specific post by postId", response = Post.class, tags = "Version 1")
    public Resource<Post> getPost(@PathVariable(name = "postId") Integer postId) {

        Optional<Post> postById = postAccessService.getPostById(postId);

        if(!postById.isPresent())
            throw new PostNotFoundException("No Post Found");

        //HATEOAS
        Resource<Post> resource = new Resource<>(postById.get());

        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllPost());
        resource.add(linkTo.withRel("all-posts"));
        return resource;

//        Normal Response Entity
//        return ResponseEntity.ok(postById.get());
    }

    @PostMapping
    @ApiOperation(value = "Add New Post", tags = "Version 1")
    public ResponseEntity<Object> addPost(@Valid @RequestBody Post post) {
        Post newPost = postAccessService.addNewPost(post);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPost.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{postId}")
    @ApiOperation(value = "Delete specific post", tags = "Version 1")
    public ResponseEntity<Object> deletePost(@PathVariable(value = "postId") Integer postId) {
        postAccessService.deletePostById(postId);
        return ResponseEntity.noContent().build();
    }
}
