package com.meli.springchallenge.dto;

import java.util.Date;
import java.util.List;

public class NewPostDTO {

    private Integer userId;
    private Integer id_post;
    private Date date;
    private List<ProductDTO> detail;
    private Integer category;
    private Double price;

    public NewPostDTO(Integer userId) {
        this.userId = userId;
    }
}
