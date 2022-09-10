package com.xtsshop.app.advices.exception;

import javax.annotation.Nullable;

public class UnAuthorizationException extends RuntimeException{

    public UnAuthorizationException(){
        super("Access Forbidden");
    }

    public UnAuthorizationException(String message) {
        super(message);
    }

    public UnAuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public static UnAuthorizationException build(@Nullable String message, Throwable cause){
        return new UnAuthorizationException(message != null ? message : "Access forbidden", cause);
    }
    public static UnAuthorizationException build(@Nullable String message){
        return new UnAuthorizationException(message != null ? message : "Access forbidden");
    }
}
