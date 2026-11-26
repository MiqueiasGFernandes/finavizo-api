package com.finavizo.api.auth.domain.exceptions;

public class IncorrectUsernameOrPasswordException extends DomainException {
    public IncorrectUsernameOrPasswordException() {
        super(IncorrectUsernameOrPasswordException.class.getName(), "INCORRECT_USERNAME_OR_PASSWORD",
                "Incorrect username or password");
    }
}
