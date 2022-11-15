package com.nebula.nebula_resource.helper.exception;

public class ItemExistException extends RuntimeException{
    public ItemExistException() {
        super();
    }

    public ItemExistException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
