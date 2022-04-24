package com.xtsshop.app.advice;

import com.xtsshop.app.advice.exception.ItemPriceNotDefinedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ControllerAdvice
public class ItemPriceNotDefinedAdvice extends AbstractAdvice<ItemPriceNotDefinedException>{

    @ResponseBody
    @ExceptionHandler(ItemPriceNotDefinedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @Override
    Map<String, String> exceptionHandler(ItemPriceNotDefinedException ex){
        return super.exceptionHandler(ex);
    }
}
