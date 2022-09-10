package com.xtsshop.app.helpers;

import java.util.function.Function;

public interface CheckedFunction<T, R> {
    R apply(T t) throws Exception;

    public static <T, R> Function<T, R> wrap(CheckedFunction<T, R> checkedFunction){
        return t -> {
            try {
                return checkedFunction.apply(t);
            }catch (Exception ex){
                throw new RuntimeException(ex);
            }
        };
    }
}
