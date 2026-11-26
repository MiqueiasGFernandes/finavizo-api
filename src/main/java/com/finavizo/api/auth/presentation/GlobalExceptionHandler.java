package com.finavizo.api.auth.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.finavizo.api.auth.domain.exceptions.DomainException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DomainException.class)
    public ProblemDetail handleDomainErrors(
            DomainException exception) {
        var errorResponse = ProblemDetail.forStatusAndDetail(HttpCodesEnum.valueOf(exception.code).getStatus(),
                exception.getMessage());

        errorResponse.setTitle(exception.title);
        errorResponse.setProperty("code", exception.code);

        return errorResponse;
    }

    @ExceptionHandler(RuntimeException.class)
    public ProblemDetail handleRuntimeExceptions(
            RuntimeException exception) {
        var errorResponse = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());

        errorResponse.setTitle("InternalServerError");
        errorResponse.setProperty("code", "INTERNAL_SERVER_ERROR");

        return errorResponse;
    }
}
