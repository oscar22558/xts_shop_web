package com.xtsshop.app.features.orders.exceptions;

import javax.annotation.Nullable;

public class ItemOutOfStockException extends RuntimeException {

    public ItemOutOfStockException(String message) {
        super(message);
    }
    public ItemOutOfStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public static ItemOutOfStockException build(@Nullable String message, Throwable cause) {
        return new ItemOutOfStockException(message != null ? message : "Item out of stock", cause);
    }

    public static ItemOutOfStockException build(@Nullable String message) {
        return new ItemOutOfStockException(message != null ? message : "Item out of stock");
    }

    public static ItemOutOfStockException build() {
        return new ItemOutOfStockException("Item out of stock");

    }
}