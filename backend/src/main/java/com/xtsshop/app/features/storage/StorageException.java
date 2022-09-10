package com.xtsshop.app.features.storage;

import javax.annotation.Nullable;

public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }

    public static StorageException build(@Nullable String message, Throwable cause){
        return new StorageException(message != null ? message : "Storage Exception", cause);
    }
    public static StorageException build(@Nullable String message){
        return new StorageException(message != null ? message : "Storage Exception");
    }
}