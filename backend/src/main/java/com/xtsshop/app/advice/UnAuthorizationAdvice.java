package com.xtsshop.app.advice;

import com.xtsshop.app.advice.exception.UnAuthenticationException;
import com.xtsshop.app.advice.exception.UnAuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Nullable;
import javax.validation.constraints.Null;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UnAuthorizationAdvice extends AbstractAdvice<UnAuthorizationException>{

    @ResponseBody
    @ExceptionHandler(UnAuthorizationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @Override
    Map<String, String> exceptionHandler(UnAuthorizationException ex){
        return super.exceptionHandler(ex);
    }
}
