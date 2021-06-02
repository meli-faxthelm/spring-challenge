package com.meli.springchallenge.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyFollowingException extends ResponseStatusException {
    public UserAlreadyFollowingException(){
        super(HttpStatus.BAD_REQUEST, "Você já segue esse vendedor");
    }
}
