package com.meli.springchallenge.exception;

import com.meli.springchallenge.exception.post.PostIdAlreadyExistsException;
import com.meli.springchallenge.exception.user.UserAlreadyFollowingException;
import com.meli.springchallenge.exception.user.UserCantBeFollowedException;
import com.meli.springchallenge.exception.user.UserCantFollowSelfException;
import com.meli.springchallenge.exception.user.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler {


    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<String> handleUserNotFoundException(UserNotFoundException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getReason());
    }

    @ExceptionHandler(UserAlreadyFollowingException.class)
    protected ResponseEntity<String> handleUserAlreadyFollowingException(UserAlreadyFollowingException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getReason());
    }

    @ExceptionHandler(UserCantBeFollowedException.class)
    protected ResponseEntity<String> handleUserCantBeFollowedException(UserCantBeFollowedException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getReason());
    }

    @ExceptionHandler(UserCantFollowSelfException.class)
    protected ResponseEntity<String> handleUserCantFollowSelfException(UserCantFollowSelfException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getReason());
    }

    @ExceptionHandler(PostIdAlreadyExistsException.class)
    protected ResponseEntity<String> handlePostIdAlreadyExistsException(PostIdAlreadyExistsException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getReason());
    }


}
