package com.xtsshop.app.advices.exception;

import javax.annotation.Nullable;

public class UnAuthenticationException extends RuntimeException{

    public UnAuthenticationException(String message) {
        super(message);
    }

    public UnAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
    public static UnAuthenticationException build(@Nullable String message, Throwable cause){
        return new UnAuthenticationException(message != null ? message : "Unauthenticated", cause);
    }
    public static UnAuthenticationException build(@Nullable String message){
        return new UnAuthenticationException(message != null ? message : "Unauthenticated");
    }
}
