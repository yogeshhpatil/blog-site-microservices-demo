package com.blog.application.servicearticlelibrary.model;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class Article {

    private Integer id;

    @NotBlank(message = "userId can not be empty")
    private String userId;

    @NotBlank(message = "article title can not be empty")
    private String title;

    @NotBlank(message = "article body can not be empty")
    private String body;

    private Date postingDate;

    private CommentList commentList;

    public Article() { }

    public CommentList getCommentList() {
        return commentList;
    }

    public void setCommentList(CommentList commentList) {
        this.commentList = commentList;
    }

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
