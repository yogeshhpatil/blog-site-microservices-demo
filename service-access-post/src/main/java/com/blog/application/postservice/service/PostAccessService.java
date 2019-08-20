package com.blog.application.postservice.service;

import com.blog.application.postservice.dao.PostDAO;
import com.blog.application.postservice.model.Post;
import com.blog.application.postservice.model.PostList;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PostAccessService {

    PostDAO postDAO;

    public PostAccessService(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    public PostList getAllPost() {
        PostList postList = new PostList();
        postList.setPosts(postDAO.getAllPost());
        return postList;
    }

    public PostList getAllPostByUser(String userId) {
        PostList postList = new PostList();
        postList.setPosts(postDAO.getAllPostByUser(userId));
        return postList;
    }

    public Optional<Post> getPostById(Integer postId) {
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
