package com.blog.application.postservice.repository;

import com.blog.application.postservice.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostMysqlRepository extends CrudRepository<Post,Integer> {

}
