package com.toolrental.toolrentalproject.exceptions;

public class InvalidMethodArgumentException extends RuntimeException {
    public InvalidMethodArgumentException(String id) {
        super("You entered an invalid value: "
                + id);
    }

}
