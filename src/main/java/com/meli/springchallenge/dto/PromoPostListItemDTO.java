package com.meli.springchallenge.dto;

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
