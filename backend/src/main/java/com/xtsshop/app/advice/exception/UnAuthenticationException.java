package com.xtsshop.app.advice.exception;

import javax.annotation.Nullable;

public class UnAuthenticationException extends Exception{

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
