package com.xtsshop.app.advice.exception;

import javax.annotation.Nullable;

public class OrderStatusUpdateException extends Exception{

    public OrderStatusUpdateException(String message) {
        super(message);
    }

    public OrderStatusUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public static OrderStatusUpdateException build(@Nullable String message, Throwable cause){
        return new OrderStatusUpdateException(message != null ? message : "Order Status Update Error", cause);
    }
    public static OrderStatusUpdateException build(@Nullable String message){
        return new OrderStatusUpdateException(message != null ? message : "Order Status Update Error");
    }
}