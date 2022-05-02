package com.xtsshop.app.advice;

import com.xtsshop.app.advice.exception.UnAuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ControllerAdvice
public class UsernameNotFoundAdvice extends AbstractAdvice<org.springframework.security.core.userdetails.UsernameNotFoundException>{

    @ResponseBody
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @Override
    Map<String, String> exceptionHandler(UsernameNotFoundException ex){
        return super.exceptionHandler(ex);
    }
}
