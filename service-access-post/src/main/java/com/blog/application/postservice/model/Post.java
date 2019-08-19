package com.blog.application.postservice.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "userId can not be empty")
    private String userId;

    @NotBlank(message = "post title can not be empty")
    private String title;

    @NotBlank(message = "post body can not be empty")
    private String body;

    private Date postingDate;

    public Post() { }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

}
