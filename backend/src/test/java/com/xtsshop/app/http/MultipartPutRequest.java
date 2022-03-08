package com.xtsshop.app.http;

import com.sun.istack.NotNull;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class MultipartPutRequest {
    public static MockMultipartHttpServletRequestBuilder builder(
            @NotNull String urlTemplate,
            Object... uriVar
    ){
        MockMultipartHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.multipart(urlTemplate, uriVar);
        builder.with(request -> {
            request.setMethod("PUT");
            return request;
        });
        return builder;
    }
}
