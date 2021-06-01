package com.meli.springchallenge.controller;

import com.meli.springchallenge.dto.NewPostDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @PostMapping("newpost")
    public ResponseEntity<Void> createPost(@RequestBody NewPostDTO newPostDTO) {

        return ResponseEntity.status(200).build();
    }
}
