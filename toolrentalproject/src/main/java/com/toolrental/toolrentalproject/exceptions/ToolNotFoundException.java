package com.toolrental.toolrentalproject.exceptions;

public class ToolNotFoundException extends RuntimeException {
    public ToolNotFoundException(String id) {
        super("Could not find tool with code: " + id);
    }

}
