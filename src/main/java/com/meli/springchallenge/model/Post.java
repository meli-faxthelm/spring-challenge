package com.meli.springchallenge.model;

import com.meli.springchallenge.dto.PostFeedPostDTO;

import java.util.Date;
import java.util.Objects;

public class Post {

    private Integer userId;
    private Integer id_post;
    private Date date;
    private Product detail;
    private Integer category;
    private Double price;

    public Post(){

    }

    public Post(Integer userId, Integer id_post, Date date, Product detail, Integer category, Double price) {
        this.userId = userId;
        this.id_post = id_post;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id_post.equals(post.id_post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_post);
    }

    public PostFeedPostDTO toPostFeedPostDTO(){
        PostFeedPostDTO postFeedPostDTO = new PostFeedPostDTO();
        postFeedPostDTO.setId_post(this.id_post);
        postFeedPostDTO.setDate(this.date);
        postFeedPostDTO.setDetail(this.detail.toProductDTO());
        postFeedPostDTO.setCategory(this.category);
        postFeedPostDTO.setPrice(this.price);
        return postFeedPostDTO;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId_post() {
        return id_post;
    }

    public void setId_post(Integer id_post) {
        this.id_post = id_post;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Product getDetail() {
        return detail;
    }

    public void setDetail(Product detail) {
        this.detail = detail;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
