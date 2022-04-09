package com.xtsshop.app.advice;

import com.xtsshop.app.advice.exception.StorageFileNotFoundException;
import com.xtsshop.app.advice.exception.UnAuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class StorageFileNotFoundAdvice extends AbstractAdvice<StorageFileNotFoundException>{

    @ResponseBody
    @ExceptionHandler(StorageFileNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @Override
    Map<String, String> exceptionHandler(StorageFileNotFoundException ex){
        return super.exceptionHandler(ex);
    }
}
