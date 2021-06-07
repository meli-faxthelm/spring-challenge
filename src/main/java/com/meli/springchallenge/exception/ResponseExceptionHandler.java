package com.meli.springchallenge.exception;

import com.meli.springchallenge.exception.post.PostIdAlreadyExistsException;
import com.meli.springchallenge.exception.post.PostPromoDiscountTooBigException;
import com.meli.springchallenge.exception.user.*;
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

    @ExceptionHandler(UserNotFollowingException.class)
    protected ResponseEntity<String> handleUserNotFollowingException(UserNotFollowingException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getReason());
    }

    @ExceptionHandler(UserCantFollowSelfException.class)
    protected ResponseEntity<String> handleUserCantFollowSelfException(UserCantFollowSelfException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getReason());
    }
    @ExceptionHandler(UserIsNotSellerException.class)
    protected ResponseEntity<String> handleUserIsNotSellerException(UserIsNotSellerException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getReason());
    }



    @ExceptionHandler(PostIdAlreadyExistsException.class)
    protected ResponseEntity<String> handlePostIdAlreadyExistsException(PostIdAlreadyExistsException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getReason());
    }
    @ExceptionHandler(PostPromoDiscountTooBigException.class)
    protected ResponseEntity<String> handlePostPromoDiscountTooBigException(PostPromoDiscountTooBigException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getReason());
    }


}
