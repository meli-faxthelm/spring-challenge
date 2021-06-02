package com.meli.springchallenge.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserCantFollowSelfException extends ResponseStatusException {
    public UserCantFollowSelfException() {
        super(HttpStatus.BAD_REQUEST, "Usuário não pode seguir ele mesmo");
    }
}
