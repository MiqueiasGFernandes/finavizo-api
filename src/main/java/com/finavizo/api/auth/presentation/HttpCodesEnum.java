package com.finavizo.api.auth.presentation;

import org.springframework.http.HttpStatus;

public enum HttpCodesEnum {
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT),
    INCORRECT_USERNAME_OR_PASSWORD(HttpStatus.UNAUTHORIZED);

    private final HttpStatus httpStatus;

    private HttpCodesEnum(
            HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getStatus() {
        return this.httpStatus;
    }

}
