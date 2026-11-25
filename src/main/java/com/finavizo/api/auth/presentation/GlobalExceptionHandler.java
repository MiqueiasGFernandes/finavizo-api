package com.finavizo.api.auth.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.finavizo.api.auth.domain.exceptions.UserAlreadyExistsException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ProblemDetail handleUserAlreadyExists(
            UserAlreadyExistsException exception) {
        var errorResponse = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, exception.getMessage());

        errorResponse.setTitle("User already exists");
        errorResponse.setProperty("code", "USER_ALREADY_EXISTS");

        return errorResponse;
    }
}
