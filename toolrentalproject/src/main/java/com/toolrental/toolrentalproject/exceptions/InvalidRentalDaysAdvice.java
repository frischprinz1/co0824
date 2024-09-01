package com.toolrental.toolrentalproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InvalidRentalDaysAdvice {
    @ExceptionHandler(InvalidRentalDaysException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String invalidRentalDaysHandler(InvalidRentalDaysException ex) {
        return ex.getMessage();
    }
}