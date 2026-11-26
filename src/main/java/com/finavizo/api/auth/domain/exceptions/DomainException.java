package com.finavizo.api.auth.domain.exceptions;

public class DomainException extends RuntimeException {
    public String title;
    public String code;
    public String detail;

    public DomainException(
            String title,
            String code,
            String detail) {
        super(detail);
        this.title = title;
        this.code = code;
        this.detail = detail;
    }
}