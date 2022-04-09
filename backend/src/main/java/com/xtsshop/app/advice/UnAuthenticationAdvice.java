package com.xtsshop.app.advice;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.advice.exception.UnAuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UnAuthenticationAdvice extends AbstractAdvice<UnAuthenticationException>{

    @ResponseBody
    @ExceptionHandler(UnAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @Override
    Map<String, String> exceptionHandler(UnAuthenticationException ex){
        return super.exceptionHandler(ex);
    }
}
