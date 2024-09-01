package com.toolrental.toolrentalproject.exceptions;

public class InvalidRentalDaysException extends RuntimeException {
    public InvalidRentalDaysException(String id) {
        super("You cannot rent a tool for less than one day.\n Please enter a rental value of 1 or higher.\n You entered the value: "
                + id);
    }

}
