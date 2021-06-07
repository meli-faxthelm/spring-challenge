package com.meli.springchallenge.controller;

import com.meli.springchallenge.dto.NewPostDTO;
import com.meli.springchallenge.dto.PostFeedDTO;
import com.meli.springchallenge.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("newpost")
    public ResponseEntity<Void> createPost(@RequestBody NewPostDTO newPostDTO) {
        postService.createPost(newPostDTO);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("followed/{userId}/list")
    public ResponseEntity<PostFeedDTO> getUserPostFeed(@PathVariable Integer userId) {
        return ResponseEntity.status(200).body(postService.getUserPostsByUserId(userId));
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createTestSet() {
        postService.createTestSet();
        return ResponseEntity.status(200).build();
    }
}
