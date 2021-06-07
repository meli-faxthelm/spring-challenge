package com.meli.springchallenge.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PromoPostListItemDTO extends PostFeedPostDTO{

    private boolean hasPromo;
    private Double discount;

    public PromoPostListItemDTO(){
        super();
    }

    public boolean isHasPromo() {
        return hasPromo;
    }

    public void setHasPromo(boolean hasPromo) {
        this.hasPromo = hasPromo;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
