package com.xtsshop.app.features.users.exceptions.advices;

import com.xtsshop.app.AbstractAdvice;
import com.xtsshop.app.features.users.exceptions.UsernameExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UsernameExistsAdvice extends AbstractAdvice<UsernameExistsException> {
    @ResponseBody
    @ExceptionHandler(UsernameExistsException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @Override
    public Map<String, String> exceptionHandler(UsernameExistsException ex){
        Map<String, String> response = new HashMap<>();
        response.put("username", ex.getMessage());
        return response;
    }
}
