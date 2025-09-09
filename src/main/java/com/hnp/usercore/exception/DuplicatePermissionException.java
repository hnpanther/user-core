package com.hnp.usercore.exception;

public class DuplicatePermissionException extends RuntimeException{
    public DuplicatePermissionException(String message) {
        super(message);
    }
}
