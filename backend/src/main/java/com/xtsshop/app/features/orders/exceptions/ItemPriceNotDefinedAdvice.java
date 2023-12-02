package com.xtsshop.app.features.orders.exceptions;

import com.xtsshop.app.AbstractAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ControllerAdvice
public class ItemPriceNotDefinedAdvice extends AbstractAdvice<ItemPriceNotDefinedException> {

    @ResponseBody
    @ExceptionHandler(ItemPriceNotDefinedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @Override
    public Map<String, String> exceptionHandler(ItemPriceNotDefinedException ex){
        return super.exceptionHandler(ex);
    }
}
