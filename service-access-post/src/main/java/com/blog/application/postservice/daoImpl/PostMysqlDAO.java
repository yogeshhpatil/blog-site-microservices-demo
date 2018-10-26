package com.blog.application.postservice.daoImpl;


import com.blog.application.postservice.dao.PostDAO;
import com.blog.application.postservice.model.Comment;
import com.blog.application.postservice.model.Post;
import com.blog.application.postservice.repository.PostMysqlRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@Qualifier("mysql")
public class PostMysqlDAO implements PostDAO {

    private PostMysqlRepository postMysqlRepository;

    public PostMysqlDAO(PostMysqlRepository postMysqlRepository) {
        this.postMysqlRepository = postMysqlRepository;
    }

    @Override
    public List<Post> getAllPost() {
        Iterable<Post> allPosts = postMysqlRepository.findAll();

        List<Post> allPostList =new ArrayList<>();
        if(allPosts.iterator().hasNext()){
            allPosts.forEach(allPostList::add);
            return allPostList;
        }
        return null;
    }

    @Override
    public Optional<Post> getPostById(Integer postId) {
        return postMysqlRepository.findById(postId);
    }

    @Override
    public void deletePostById(Integer postId) {
        postMysqlRepository.deleteById(postId);
    }

    @Override
    public void addCommentToPost(Integer postId, Comment comment) {
        Optional<Post> optionalPost = postMysqlRepository.findById(postId);
        if(optionalPost.isPresent()){
            Post post = optionalPost.get();

            comment.setDate(new Date());
            post.addComment(comment);

            postMysqlRepository.save(post);
        }
    }

    @Override
    public void deleteCommentFromPost(Integer postId, Integer commentId) {
        Optional<Post> optionalPost = postMysqlRepository.findById(postId);
        if(optionalPost.isPresent()){
            Post post = optionalPost.get();
            post.removeComment(new Comment(commentId));

            postMysqlRepository.save(post);
        }
    }

    @Override
    public Post addNewPost(Post post) {
        post.setPostingDate(new Date());
        return postMysqlRepository.save(post);
    }

    @Override
    public List<Post> getPostByCategory(String category) {
        return postMysqlRepository.getPostByCategory(category);
    }
}
