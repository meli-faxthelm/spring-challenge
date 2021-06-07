package com.meli.springchallenge.service;

import com.meli.springchallenge.dto.NewPostDTO;
import com.meli.springchallenge.dto.PostFeedDTO;

import java.util.Optional;

public interface PostService {

    void createPost(NewPostDTO newPostDTO);
    PostFeedDTO getUserPostsByUserId(Integer userId, Optional<String> order);

    void createTestSet();
}
