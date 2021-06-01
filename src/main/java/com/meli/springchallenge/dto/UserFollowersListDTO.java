package com.meli.springchallenge.dto;

import java.util.List;

public class UserFollowersListDTO {
    private Integer userId;
    private String userName;
    private List<UserBasicDTO> followers;

    public UserFollowersListDTO(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<UserBasicDTO> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UserBasicDTO> followers) {
        this.followers = followers;
    }
}
