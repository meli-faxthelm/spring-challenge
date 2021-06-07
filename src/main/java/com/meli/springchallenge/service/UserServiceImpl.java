package com.meli.springchallenge.service;


import com.meli.springchallenge.dto.UserBasicDTO;
import com.meli.springchallenge.dto.UserFollowedListDTO;
import com.meli.springchallenge.dto.UserFollowersCountDTO;
import com.meli.springchallenge.dto.UserFollowersListDTO;
import com.meli.springchallenge.exception.user.*;
import com.meli.springchallenge.model.User;
import com.meli.springchallenge.model.UserBasic;
import com.meli.springchallenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void follow(Integer userId, Integer userIdToFollow) {
        User user = userRepository.findUserByUserId(userId);
        User userToFollow = userRepository.findUserByUserId(userIdToFollow);

        if (userToFollow.isSeller()) {

            if (userId.equals(userIdToFollow)) {
                throw new UserCantFollowSelfException();
            }

            if (!user.getFollowedSet().contains(userToFollow.toUserBasic())) {

                user.getFollowedSet().add(new UserBasic(userToFollow.getUserId(), userToFollow.getUserName()));
                userToFollow.getFollowerSet().add(new UserBasic(user.getUserId(), user.getUserName()));

                userRepository.save(user);
                userRepository.save(userToFollow);
                return;
            }
            throw new UserAlreadyFollowingException();
        }
        throw new UserCantBeFollowedException();
    }

    @Override
    public UserFollowersCountDTO getFollowersCount(Integer userId) {
        User user = userRepository.findUserByUserId(userId);

        if (!user.isSeller()) {
            throw new UserIsNotSellerException();
        }

        UserFollowersCountDTO userFollowersCountDTO = new UserFollowersCountDTO();

        userFollowersCountDTO.setUserId(user.getUserId());
        userFollowersCountDTO.setUserName(user.getUserName());
        userFollowersCountDTO.setFollowers_count(user.getFollowerSet().size());

        return userFollowersCountDTO;
    }

    @Override
    public UserFollowersListDTO getFollowersList(Integer userId) {
        User user = userRepository.findUserByUserId(userId);

        if (!user.isSeller()) {
            throw new UserIsNotSellerException();
        }

        UserFollowersListDTO userFollowersListDTO = new UserFollowersListDTO(userId, user.getUserName());
        List<UserBasicDTO> followerList = user.getFollowerSet().stream().map(UserBasic::toUserBasicDTO)
                .collect(Collectors.toList());
        userFollowersListDTO.setFollowers(followerList);
        return userFollowersListDTO;
    }

    @Override
    public UserFollowedListDTO getFollowedList(Integer userId) {
        User user = userRepository.findUserByUserId(userId);
        UserFollowedListDTO userFollowedListDTO = new UserFollowedListDTO(userId, user.getUserName());
        List<UserBasicDTO> followedList = user.getFollowedSet().stream().map(UserBasic::toUserBasicDTO)
                .collect(Collectors.toList());
        userFollowedListDTO.setFollowed(followedList);
        return userFollowedListDTO;
    }

    @Override
    public void unfollow(Integer userId, Integer userIdToUnfollow) {
        User user = userRepository.findUserByUserId(userId);
        User userToUnfollow = userRepository.findUserByUserId(userIdToUnfollow);

        if (!userToUnfollow.isSeller()) {
            throw new UserIsNotSellerException();
        }
        if (!userToUnfollow.getFollowerSet().contains(user.toUserBasic())) {
            throw new UserNotFollowingException();
        }
        User aux = user;
        user.getFollowedSet().remove(userToUnfollow.toUserBasic());
        userToUnfollow.getFollowerSet().remove(aux.toUserBasic());

        userRepository.save(user);
        userRepository.save(userToUnfollow);
    }


    @Override
    public void createTestSet() {
        User consumer1 = new User(1, "meli1", false);
        User consumer2 = new User(2, "meli2", false);
        User consumer3 = new User(3, "meli3", false);
        User consumer4 = new User(4, "meli4", false);
        User consumer5 = new User(5, "meli5", false);
        User seller1 = new User(6, "seller1", true);
        User seller2 = new User(7, "seller2", true);
        User seller3 = new User(8, "seller3", true);
        userRepository.save(consumer1);
        userRepository.save(consumer2);
        userRepository.save(consumer3);
        userRepository.save(consumer4);
        userRepository.save(consumer5);
        userRepository.save(seller1);
        userRepository.save(seller2);
        userRepository.save(seller3);
    }

}
