package com.nebula.nebula_resource.helper.exception;

public class NotAvailableException extends RuntimeException{
    public NotAvailableException() {
        super();
    }

    public NotAvailableException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
