package com.blog.application.postservice.daoImpl;

import com.blog.application.postservice.dao.PostDAO;
import com.blog.application.postservice.model.Post;
import com.blog.application.postservice.repository.PostMysqlRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Primary
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
        return allPostList;
    }

    @Override
    public Optional<Post> getPostById(Integer postId) {
        return postMysqlRepository.findById(postId);
    }

    @Override
    public Post addNewPost(Post post) {
        return postMysqlRepository.save(post);
    }

    @Override
    public boolean deletePostById(Integer postId) {
        postMysqlRepository.deleteById(postId);
        return true;
    }
}
