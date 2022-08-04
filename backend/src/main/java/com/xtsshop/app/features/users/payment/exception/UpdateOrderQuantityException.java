package com.xtsshop.app.features.users.payment.exception;

import javax.validation.constraints.Null;

public class UpdateOrderQuantityException extends RuntimeException{
    public UpdateOrderQuantityException(@Null String message){
        super(message);
    }
}
