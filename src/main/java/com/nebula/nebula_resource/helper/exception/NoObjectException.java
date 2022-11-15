package com.nebula.nebula_resource.helper.exception;

public class NoObjectException extends RuntimeException{
    public NoObjectException() {
    }

    public NoObjectException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
