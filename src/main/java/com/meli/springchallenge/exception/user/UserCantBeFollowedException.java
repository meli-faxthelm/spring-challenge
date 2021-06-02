package com.meli.springchallenge.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserCantBeFollowedException extends ResponseStatusException {

    public UserCantBeFollowedException() {
        super(HttpStatus.BAD_REQUEST, "Não é possível seguir usuários que não são vendedores");
    }
}
