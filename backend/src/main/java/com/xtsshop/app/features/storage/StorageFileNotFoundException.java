package com.xtsshop.app.features.storage;

import javax.annotation.Nullable;

public class StorageFileNotFoundException extends StorageException {

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public static StorageFileNotFoundException build(@Nullable String message, Throwable cause){
        return new StorageFileNotFoundException(message != null ? message : "File not found", cause);
    }
    public static StorageFileNotFoundException build(@Nullable String message){
        return new StorageFileNotFoundException(message != null ? message : "File not found");
    }
}