package com.xtsshop.app.advices;

import com.xtsshop.app.AbstractAdvice;
import com.xtsshop.app.advices.exception.UnAuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ControllerAdvice
public class UnAuthorizationExceptionAdvice extends AbstractAdvice<UnAuthorizationException> {

    @ResponseBody
    @ExceptionHandler(UnAuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @Override
    public Map<String, String> exceptionHandler(UnAuthorizationException ex){
        return super.exceptionHandler(ex);
    }
}
