package com.xtsshop.app.advices;

import com.xtsshop.app.AbstractAdvice;
import com.xtsshop.app.advices.exception.UnAuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ControllerAdvice
public class UnAuthenticationExceptionAdvice extends AbstractAdvice<UnAuthenticationException> {

    @ResponseBody
    @ExceptionHandler(UnAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @Override
    public Map<String, String> exceptionHandler(UnAuthenticationException ex){
        return super.exceptionHandler(ex);
    }
}
