package com.meli.springchallenge.dto;

import java.util.List;

public class PromoPostListDTO extends UserBasicDTO{

    private List<PromoPostListItemDTO> posts;

    public PromoPostListDTO() {
        super();
    }
    public PromoPostListDTO(Integer userId, String userName) {
        super(userId, userName);
    }

    public List<PromoPostListItemDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PromoPostListItemDTO> posts) {
        this.posts = posts;
    }
}
