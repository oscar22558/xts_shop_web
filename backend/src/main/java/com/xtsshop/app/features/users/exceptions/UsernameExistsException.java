package com.xtsshop.app.features.users.exceptions;

public class UsernameExistsException extends RuntimeException {
    public UsernameExistsException(){
        super(RegistryErrorCode.USERNAME_EXISTS);
    }
}
