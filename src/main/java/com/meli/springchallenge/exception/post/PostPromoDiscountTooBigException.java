package com.meli.springchallenge.exception.post;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PostPromoDiscountTooBigException extends ResponseStatusException {
    public PostPromoDiscountTooBigException() {
        super(HttpStatus.BAD_REQUEST, "O valor de desconto não pode ser maior que 0.99");
    }
}
