package com.finavizo.api.auth.presentation;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    };

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleInputExceptions(
            MethodArgumentNotValidException exception) {
        var errorResponse = ProblemDetail.forStatusAndDetail(HttpStatus.PRECONDITION_FAILED, exception.getMessage());

        errorResponse.setProperty("code", "INPUT_VALIDATION_ERROR");
        errorResponse.setTitle("InputValidationException");

        var errors = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> Map.of(
                        "field", error.getField(),
                        "message", error.getDefaultMessage()))
                .toList();

        errorResponse.setProperty("errors", errors);
        return errorResponse;
    }
}
