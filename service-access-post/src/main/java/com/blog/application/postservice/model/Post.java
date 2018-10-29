package com.blog.application.postservice.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer postId;

    @NotBlank
    private String heading;

    @NotBlank
    private String text;

    private Date postingDate;

    @NotBlank
    private String writtenBy;

    @NotBlank
    private String category;

    @OneToMany(cascade = CascadeType.ALL , orphanRemoval = true)
    @JoinColumn
    private List<Comment> comments = new ArrayList<>();

    public Post() { }

    public Post(@NotBlank String heading, @NotBlank String text, Date postingDate, @NotBlank String writtenBy, @NotBlank String category) {
        this.heading = heading;
        this.text = text;
        this.postingDate = postingDate;
        this.writtenBy = writtenBy;
        this.category = category;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public String getWrittenBy() {
        return writtenBy;
    }

    public void setWrittenBy(String writtenBy) {
        this.writtenBy = writtenBy;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment){
        comments.add(comment);
    }

    public boolean removeComment(Comment comment){
        return comments.remove(comment);
    }
}
