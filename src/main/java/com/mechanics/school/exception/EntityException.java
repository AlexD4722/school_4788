package com.mechanics.school.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EntityException {
    private final String message;
    private final Throwable throwable;
    private final int httpStatus;

    public EntityException(String message, Throwable throwable, int httpStatus) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatus = httpStatus;
    }

}
