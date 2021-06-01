package com.meli.springchallenge.service;

import com.meli.springchallenge.dto.UserFollowedListDTO;
import com.meli.springchallenge.dto.UserFollowersCountDTO;
import com.meli.springchallenge.dto.UserFollowersListDTO;

public interface UserService {

    boolean follow(Integer userId, Integer userIdToFollow) throws Exception;
    UserFollowersCountDTO getFollowersCount(Integer userId) throws Exception;
    UserFollowersListDTO getFollowersList(Integer userId) throws Exception;
    UserFollowedListDTO getFollowedList(Integer userId) throws Exception;


    void createTestSet() throws Exception;
}
