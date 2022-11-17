package com.nebula.nebula_resource.helper.exception;

public class LackMoneyException extends RuntimeException{
    public LackMoneyException() {
        super();
    }

    public LackMoneyException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
