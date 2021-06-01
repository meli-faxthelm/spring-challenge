package com.meli.springchallenge.controller;

import com.meli.springchallenge.dto.UserFollowedListDTO;
import com.meli.springchallenge.dto.UserFollowersCountDTO;
import com.meli.springchallenge.dto.UserFollowersListDTO;
import com.meli.springchallenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("{userId}/follow/{userIdToFollow}")
    public ResponseEntity<Void> followUserById(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) throws Exception {
        if(userService.follow(userId, userIdToFollow)) {
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(400).build();
    }

    @GetMapping("{userId}/followers/count")
    public ResponseEntity<UserFollowersCountDTO> followersCount(@PathVariable Integer userId) throws Exception {
        return ResponseEntity.status(200).body(userService.getFollowersCount(userId));
    }


    @GetMapping("{userId}/followers/list")
    public ResponseEntity<UserFollowersListDTO> followersList(@PathVariable Integer userId) throws Exception {
        return ResponseEntity.status(200).body(userService.getFollowersList(userId));
    }

    @GetMapping("{userId}/followed/list")
    public ResponseEntity<UserFollowedListDTO> followedList(@PathVariable Integer userId) throws Exception {
        return ResponseEntity.status(200).body(userService.getFollowedList(userId));
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createDataTest() throws Exception {
        userService.createTestSet();
        return ResponseEntity.status(200).build();
    }

}
