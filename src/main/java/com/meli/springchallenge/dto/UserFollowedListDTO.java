package com.meli.springchallenge.dto;

import java.util.List;

public class UserFollowedListDTO {
    private Integer userId;
    private String userName;
    private List<UserBasicDTO> followed;

    public UserFollowedListDTO(Integer userId, String userName) {
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

    public List<UserBasicDTO> getFollowed() {
        return followed;
    }

    public void setFollowed(List<UserBasicDTO> followerList) {
        this.followed = followerList;
    }
}
