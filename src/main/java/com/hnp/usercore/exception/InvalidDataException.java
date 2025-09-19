package com.hnp.usercore.exception;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String message) {
        super("Invalid Data " + message);
    }
}
