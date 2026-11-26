package com.finavizo.api.auth.domain.exceptions;

public class UserAlreadyExistsException extends DomainException {
    public UserAlreadyExistsException(String username) {
        super(UserAlreadyExistsException.class.getName(),
                "USER_ALREADY_EXISTS",
                "User with username %s already exists. Please try to use another one.".formatted(
                        username));
    }
}
