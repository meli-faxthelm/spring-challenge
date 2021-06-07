package com.meli.springchallenge.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFollowingException extends ResponseStatusException {
    public UserNotFollowingException() {
        super(HttpStatus.BAD_REQUEST, "Você já não segue esse vendedor");
    }
}
