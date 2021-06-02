package com.meli.springchallenge.dto;

import com.meli.springchallenge.model.Product;

import java.util.List;

public class PostFeedDTO {
    private Integer userId;
    private List<ProductDTO> posts;

    public PostFeedDTO() {

    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<ProductDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<ProductDTO> posts) {
        this.posts = posts;
    }
}
