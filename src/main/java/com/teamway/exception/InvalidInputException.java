package com.teamway.exception;

public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException() {

    }
}
