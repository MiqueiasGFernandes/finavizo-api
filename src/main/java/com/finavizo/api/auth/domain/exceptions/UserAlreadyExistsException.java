package com.finavizo.api.auth.domain.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String email) {
        super("User with username %s already exists. Please try to use another one.".formatted(
            email
        ));
    }
}
