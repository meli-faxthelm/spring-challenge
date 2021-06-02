package com.meli.springchallenge.converter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.meli.springchallenge.dto.NewPostDTO;
import com.meli.springchallenge.dto.ProductDTO;
import com.meli.springchallenge.model.Post;
import com.meli.springchallenge.model.Product;

import java.util.Date;

public class DTOConverter {

    public static Post newPostDTOToPost(NewPostDTO newPostDTO) {
        Post post = new Post();
        post.setUserId(newPostDTO.getUserId());
        post.setId_post(newPostDTO.getId_post());
        post.setDate(newPostDTO.getDate());
        post.setDetail(DTOConverter.productDTOToProduct(newPostDTO.getDetail()));
        post.setCategory(newPostDTO.getCategory());
        post.setPrice(newPostDTO.getPrice());

        return post;
    }

    public static Product productDTOToProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setProduct_id(productDTO.getProduct_id());
        product.setProductName(productDTO.getProductName());
        product.setType(productDTO.getType());
        product.setBrand(productDTO.getBrand());
        product.setColor(productDTO.getColor());
        product.setNotes(productDTO.getNotes());

        return product;
    }
}
