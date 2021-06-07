package com.meli.springchallenge.dto;

import com.meli.springchallenge.model.Post;

import java.util.List;

public class PostFeedDTO {
    private Integer userId;
    private List<PostFeedPostDTO> posts;

    public PostFeedDTO() {

    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<PostFeedPostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostFeedPostDTO> posts) {
        this.posts = posts;
    }
}
