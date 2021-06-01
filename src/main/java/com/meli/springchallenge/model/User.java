package com.meli.springchallenge.model;

import java.util.HashSet;
import java.util.Set;

public class User {
    private Integer userId;
    private String userName;
    private Set<UserBasic> followedSet;
    private Set<UserBasic> followerSet;
    private boolean seller;

    public User(){

    }
    public User(Integer userId, String userName, boolean seller) {
        this.userId = userId;
        this.userName = userName;
        this.seller = seller;
        this.followedSet = new HashSet<>();
        this.followerSet = new HashSet<>();
    }

    public UserBasic toUserBasic() {
        return new UserBasic(this.userId, this.userName);
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

    public Set<UserBasic> getFollowedSet() {
        return followedSet;
    }

    public void setFollowedSet(Set<UserBasic> followedSet) {
        this.followedSet = followedSet;
    }

    public Set<UserBasic> getFollowerSet() {
        return followerSet;
    }

    public void setFollowerSet(Set<UserBasic> followerSet) {
        this.followerSet = followerSet;
    }

    public boolean isSeller() {
        return seller;
    }

}
