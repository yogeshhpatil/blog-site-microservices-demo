package com.blog.application.postservice.dao;

import com.blog.application.postservice.model.Comment;
import com.blog.application.postservice.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostDAO {
    List<Post> getAllPost();

    Optional<Post> getPostById(Integer postId);

    void deletePostById(Integer postId);

    void addCommentToPost(Integer postId, Comment comment);

    void deleteCommentFromPost(Integer postId, Integer commentId);

    Post addNewPost(Post post);

    List<Post> getPostByCategory(String category);

}
