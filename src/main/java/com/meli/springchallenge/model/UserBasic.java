package com.meli.springchallenge.model;

import com.meli.springchallenge.dto.UserBasicDTO;

public class UserBasic {

    private Integer userId;
    private String userName;

    public UserBasic(){

    }

    public UserBasic(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public UserBasicDTO toUserBasicDTO(){
        return new UserBasicDTO(this.userId, this.userName);
    }

    @Override
    public boolean equals(Object object){
        if (object == this) {
            return true;
        }
        if (!(object instanceof UserBasic)) {
            return false;
        }
        UserBasic userBasic = (UserBasic) object;
        return this.userId == userBasic.userId;
    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
}
