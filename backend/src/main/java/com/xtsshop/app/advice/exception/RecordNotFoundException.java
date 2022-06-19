package com.xtsshop.app.advice.exception;

import javax.annotation.Nullable;

public class RecordNotFoundException extends RuntimeException{

    public RecordNotFoundException(String message) {
        super(message);
    }

    public RecordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public static RecordNotFoundException build(@Nullable String message, Throwable cause){
        return new RecordNotFoundException(message != null ? message : "Record not found", cause);
    }
    public static RecordNotFoundException build(@Nullable String message){
        return new RecordNotFoundException(message != null ? message : "Record not found");
    }
}
