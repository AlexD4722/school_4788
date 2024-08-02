package com.mechanics.school.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EntityNotFoundExceptionHandler {
    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException
            (EntityNotFoundException cloudVendorNotFoundException)
    {
        EntityException entityException = new EntityException(
                cloudVendorNotFoundException.getMessage(),
                cloudVendorNotFoundException.getCause(),
                HttpStatus.NOT_FOUND.value()
        );

        return new ResponseEntity<>(entityException, HttpStatus.NOT_FOUND);
    }
}
