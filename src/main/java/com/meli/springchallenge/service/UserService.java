package com.meli.springchallenge.service;

import com.meli.springchallenge.dto.UserFollowedListDTO;
import com.meli.springchallenge.dto.UserFollowersCountDTO;
import com.meli.springchallenge.dto.UserFollowersListDTO;

import java.util.Optional;

public interface UserService {

    void follow(Integer userId, Integer userIdToFollow);
    UserFollowersCountDTO getFollowersCount(Integer userId);
    UserFollowersListDTO getFollowersList(Integer userId, Optional<String> order);
    UserFollowedListDTO getFollowedList(Integer userId, Optional<String> order);
    void unfollow(Integer userId, Integer userIdToUnfollow);


    void createTestSet();
}
