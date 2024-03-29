package com.xtsshop.app;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractAdvice<E extends Exception> {
    public Map<String, String> exceptionHandler(E ex){
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return response;
    }
}
