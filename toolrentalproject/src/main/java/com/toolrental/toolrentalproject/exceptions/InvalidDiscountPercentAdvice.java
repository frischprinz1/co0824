package com.toolrental.toolrentalproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InvalidDiscountPercentAdvice {
    @ExceptionHandler(InvalidDiscountPercentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String invalidDiscountRateHandler(InvalidDiscountPercentException ex) {
        return ex.getMessage();
    }
}