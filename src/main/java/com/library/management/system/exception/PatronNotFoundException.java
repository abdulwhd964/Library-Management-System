package com.library.management.system.exception;

public class PatronNotFoundException extends RuntimeException {

    public PatronNotFoundException(String message) {
        super(message);
    }
}
