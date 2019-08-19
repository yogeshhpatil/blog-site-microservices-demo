package com.blog.application.postservice.dao;

import com.blog.application.postservice.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostDAO {
    List<Post> getAllPost();

    Optional<Post> getPostById(Integer postId);

    Post addNewPost(Post post);

    boolean deletePostById(Integer postId);
}
