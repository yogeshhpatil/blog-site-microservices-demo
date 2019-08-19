package com.blog.application.postservice.service;

import com.blog.application.postservice.dao.PostDAO;
import com.blog.application.postservice.model.Post;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public Post addNewPost(Post post) {
        post.setPostingDate(new Date());
        return postDAO.addNewPost(post);
    }

    public void deletePostById(Integer postId) {
        postDAO.deletePostById(postId);
    }
}
