package com.meli.springchallenge.dto;

public class PromoCountDTO {
    private Integer userId;
    private String userName;
    private Integer promoproducts_count;

    public PromoCountDTO() {
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

    public Integer getPromoproducts_count() {
        return promoproducts_count;
    }

    public void setPromoproducts_count(Integer promoproducts_count) {
        this.promoproducts_count = promoproducts_count;
    }
}
