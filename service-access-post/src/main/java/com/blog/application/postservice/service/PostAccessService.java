package com.blog.application.postservice.service;


import com.blog.application.postservice.dao.PostDAO;
import com.blog.application.postservice.model.Comment;
import com.blog.application.postservice.model.Post;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostAccessService {

    PostDAO postDAO;

    public PostAccessService(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    public List<Post> getAllPost(){
        return postDAO.getAllPost();
    }

    public Optional<Post> getPostById(Integer postId){
        return postDAO.getPostById(postId);
    }

    public void deletePostById(Integer postId) {
        postDAO.deletePostById(postId);
    }

    public Post addCommentToPost(Integer postId, Comment comment) {
        return postDAO.addCommentToPost(postId,comment);
    }

    public boolean deleteCommentFromPost(Integer postId, Integer commentId) {
        return postDAO.deleteCommentFromPost(postId,commentId);
    }

    public Post addNewPost(Post post) {
        return postDAO.addNewPost(post);
    }

    public List<Post> getPostByCategory(String category) {
        return postDAO.getPostByCategory(category);
    }
}
