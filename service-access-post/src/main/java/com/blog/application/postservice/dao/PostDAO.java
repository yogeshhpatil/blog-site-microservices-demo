package com.blog.application.postservice.dao;

import com.blog.application.postservice.model.Comment;
import com.blog.application.postservice.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostDAO {
    List<Post> getAllPost();

    Optional<Post> getPostById(Integer postId);

    boolean deletePostById(Integer postId);

    Post addCommentToPost(Integer postId, Comment comment);

    boolean deleteCommentFromPost(Integer postId, Integer commentId);

    Post addNewPost(Post post);

    List<Post> getPostByCategory(String category);

}
