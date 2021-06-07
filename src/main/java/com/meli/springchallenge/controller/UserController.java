package com.meli.springchallenge.controller;

import com.meli.springchallenge.dto.UserFollowedListDTO;
import com.meli.springchallenge.dto.UserFollowersCountDTO;
import com.meli.springchallenge.dto.UserFollowersListDTO;
import com.meli.springchallenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("{userId}/follow/{userIdToFollow}")
    public ResponseEntity<Void> followUserById(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) {
        userService.follow(userId, userIdToFollow);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("{userId}/followers/count")
    public ResponseEntity<UserFollowersCountDTO> followersCount(@PathVariable Integer userId) {
        return ResponseEntity.status(200).body(userService.getFollowersCount(userId));
    }


    @GetMapping("{userId}/followers/list")
    public ResponseEntity<UserFollowersListDTO> followersList(@PathVariable Integer userId, @RequestParam Optional<String> order) {
        return ResponseEntity.status(200).body(userService.getFollowersList(userId, order));
    }

    @GetMapping("{userId}/followed/list")
    public ResponseEntity<UserFollowedListDTO> followedList(@PathVariable Integer userId, @RequestParam Optional<String> order) {
        return ResponseEntity.status(200).body(userService.getFollowedList(userId, order));
    }

    @PostMapping("{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<Void> unfollowUser(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow) {
        userService.unfollow(userId, userIdToUnfollow);
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createDataTest() {
        userService.createTestSet();
        return ResponseEntity.status(200).build();
    }

}
