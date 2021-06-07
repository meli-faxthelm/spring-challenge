package com.meli.springchallenge.service;

import com.meli.springchallenge.dto.*;

import java.util.Optional;

public interface PostService {

    void createPost(NewPostDTO newPostDTO);
    PostFeedDTO getUserPostsByUserId(Integer userId, Optional<String> order);
    void createPromoPost(NewPromoPostDTO newPromoPostDTO);
    PromoCountDTO promoCountByUserId(Integer userId);
    PromoPostListDTO promoList(Integer userId);

    void createTestSet();
}
