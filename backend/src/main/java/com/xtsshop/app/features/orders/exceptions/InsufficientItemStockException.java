package com.xtsshop.app.features.orders.exceptions;

import javax.annotation.Nullable;

public class InsufficientItemStockException extends RuntimeException {

    public InsufficientItemStockException(String message) {
        super(message);
    }
    public InsufficientItemStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public static InsufficientItemStockException build(@Nullable String message, Throwable cause) {
        return new InsufficientItemStockException(message != null ? message : "Order quantity exceeds stock", cause);
    }

    public static InsufficientItemStockException build(@Nullable String message) {
        return new InsufficientItemStockException(message != null ? message : "Order quantity exceeds stock");
    }

    public static InsufficientItemStockException build() {
        return new InsufficientItemStockException("Order quantity exceeds stock");

    }
}