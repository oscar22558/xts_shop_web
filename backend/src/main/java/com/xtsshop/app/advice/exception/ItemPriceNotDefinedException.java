package com.xtsshop.app.advice.exception;

import javax.annotation.Nullable;

public class ItemPriceNotDefinedException extends Exception{

    public ItemPriceNotDefinedException(String message) {
        super(message);
    }

    public ItemPriceNotDefinedException(String message, Throwable cause) {
        super(message, cause);
    }

    public static ItemPriceNotDefinedException build(@Nullable String message, Throwable cause){
        return new ItemPriceNotDefinedException(message != null ? message : "Order Price not defined", cause);
    }
    public static ItemPriceNotDefinedException build(@Nullable String message){
        return new ItemPriceNotDefinedException(message != null ? message : "Order Price not defined");
    }
}
