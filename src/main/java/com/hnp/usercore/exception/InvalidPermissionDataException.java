package com.hnp.usercore.exception;

public class InvalidPermissionDataException extends RuntimeException {
    public InvalidPermissionDataException(String message) {
        super("Invalid Data Permission " + message);
    }
}
