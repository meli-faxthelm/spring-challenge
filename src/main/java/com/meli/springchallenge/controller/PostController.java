package com.meli.springchallenge.controller;

import com.meli.springchallenge.dto.*;
import com.meli.springchallenge.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public ResponseEntity<PostFeedDTO> getUserPostFeed(@PathVariable Integer userId, @RequestParam Optional<String> order) {
        return ResponseEntity.status(200).body(postService.getUserPostsByUserId(userId, order));
    }

    @PostMapping("newpromopost")
    public ResponseEntity<Void> createPromoPost(@RequestBody NewPromoPostDTO newPromoPostDTO) {
        postService.createPromoPost(newPromoPostDTO);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("{userId}/countPromo")
    public ResponseEntity<PromoCountDTO> promoCount(@PathVariable Integer userId) {
        return ResponseEntity.status(200).body(postService.promoCountByUserId(userId));
    }

    @GetMapping("{userId}/list")
    public ResponseEntity<PromoPostListDTO> promoList(@PathVariable Integer userId) {
        return ResponseEntity.status(200).body(postService.promoList(userId));
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createTestSet() {
        postService.createTestSet();
        return ResponseEntity.status(200).build();
    }
}
