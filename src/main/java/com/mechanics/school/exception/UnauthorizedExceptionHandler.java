package com.mechanics.school.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UnauthorizedExceptionHandler extends RuntimeException {

    private final HttpStatus httpStatus;

    public UnauthorizedExceptionHandler(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
