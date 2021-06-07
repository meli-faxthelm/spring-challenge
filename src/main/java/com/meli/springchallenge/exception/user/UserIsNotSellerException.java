package com.meli.springchallenge.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserIsNotSellerException extends ResponseStatusException {
    public UserIsNotSellerException() {
        super(HttpStatus.BAD_REQUEST, "O usuário não é vendedor");
    }
}
