package com.xtsshop.app.advices;

import com.xtsshop.app.AbstractAdvice;
import com.xtsshop.app.advices.exception.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ControllerAdvice
public class RecordNotFoundExceptionAdvice extends AbstractAdvice<RecordNotFoundException> {

    @ResponseBody
    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @Override
    public Map<String, String> exceptionHandler(RecordNotFoundException ex) {
        return super.exceptionHandler(ex);
    }
}
