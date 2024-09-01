package com.toolrental.toolrentalproject.exceptions;

public class InvalidDiscountPercentException extends RuntimeException {
    public InvalidDiscountPercentException(String id) {
        super("Please enter a discount value in the range of 0 - 100.\n You entered the value: "
                + id);
    }

}
