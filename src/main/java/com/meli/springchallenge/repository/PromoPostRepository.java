package com.meli.springchallenge.repository;


import com.meli.springchallenge.model.PromoPostDetail;

import java.util.Set;

public interface PromoPostRepository {

    void save(PromoPostDetail promoPostDetail);

    Set<PromoPostDetail> findPromoPostDetailsByUserId(Integer userId);

}
