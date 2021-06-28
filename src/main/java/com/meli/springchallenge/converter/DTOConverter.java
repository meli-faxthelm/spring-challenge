package com.meli.springchallenge.converter;

import com.meli.springchallenge.dto.NewPostDTO;
import com.meli.springchallenge.dto.NewPromoPostDTO;
import com.meli.springchallenge.dto.ProductDTO;
import com.meli.springchallenge.model.Post;
import com.meli.springchallenge.model.Product;
import com.meli.springchallenge.model.PromoPostDetail;

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

    public static Post newPromoPostDTOToPost(NewPromoPostDTO newPromoPostDTO) {
        Post post = new Post();
        post.setUserId(newPromoPostDTO.getUserId());
        post.setId_post(newPromoPostDTO.getId_post());
        post.setDate(newPromoPostDTO.getDate());
        post.setDetail(DTOConverter.productDTOToProduct(newPromoPostDTO.getDetail()));
        post.setCategory(newPromoPostDTO.getCategory());
        post.setPrice(newPromoPostDTO.getPrice());

        return post;
    }

    public static PromoPostDetail newPromoPostDTOToPromoPostDetail(NewPromoPostDTO newPromoPostDTO) {
        PromoPostDetail promoPostDetail = new PromoPostDetail();
        promoPostDetail.setUserId(newPromoPostDTO.getUserId());
        promoPostDetail.setId_post(newPromoPostDTO.getId_post());
        promoPostDetail.setHasPromo(newPromoPostDTO.isHasPromo());
        promoPostDetail.setDiscount(newPromoPostDTO.getDiscount());

        return promoPostDetail;
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
