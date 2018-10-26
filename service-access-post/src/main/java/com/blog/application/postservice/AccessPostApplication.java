package com.blog.application.postservice;

import com.blog.application.postservice.model.Comment;
import com.blog.application.postservice.model.Post;
import com.blog.application.postservice.repository.PostMysqlRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
//@EnableEurekaClient
public class AccessPostApplication implements CommandLineRunner {

	PostMysqlRepository postMysqlRepository;

	public AccessPostApplication(PostMysqlRepository postMysqlRepository) {
		this.postMysqlRepository = postMysqlRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(AccessPostApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Post post = new Post();

		post.setHeading("heading of post1");
		post.setPostingDate(new Date());
		post.setText("contains of blog post1");
		post.setWrittenBy("author of post1");
		post.setCategory("tech");
		post.addComment(new Comment("comment text","usernametext","email@gmail.com",new Date()));
		post.addComment(new Comment("comment text2","usernametext2","email2@gmail.com",new Date()));

		postMysqlRepository.save(post);

		post = new Post();

		post.setHeading("heading of post2");
		post.setPostingDate(new Date());
		post.setText("contains of blog post2");
		post.setWrittenBy("author of post2");
		post.setCategory("test");
		post.addComment(new Comment("comment text3","usernametext3","email3@gmail.com",new Date()));
		post.addComment(new Comment("comment text4","usernametext4","email4@gmail.com",new Date()));

		postMysqlRepository.save(post);
	}
}
