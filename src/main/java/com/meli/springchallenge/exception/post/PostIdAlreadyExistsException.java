package com.meli.springchallenge.exception.post;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PostIdAlreadyExistsException extends ResponseStatusException {
    public PostIdAlreadyExistsException() {
        super(HttpStatus.BAD_REQUEST, "PostId existente para esse vendedor");
    }
}
