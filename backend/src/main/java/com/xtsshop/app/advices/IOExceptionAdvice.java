package com.xtsshop.app.advices;

import com.xtsshop.app.AbstractAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.util.Map;

@ControllerAdvice
public class IOExceptionAdvice extends AbstractAdvice<IOException> {

    @ResponseBody
    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @Override
    public Map<String, String> exceptionHandler(IOException ex) {
        return super.exceptionHandler(ex);
    }
}
