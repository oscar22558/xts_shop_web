package com.xtsshop.app.advice.exception;

public class RecordNotFoundException extends Exception{

    public RecordNotFoundException(String message) {
        super(message);
    }

    public RecordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
