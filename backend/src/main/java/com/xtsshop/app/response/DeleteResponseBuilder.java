package com.xtsshop.app.response;

import org.springframework.http.ResponseEntity;

public class DeleteResponseBuilder{
    public ResponseEntity<?> build(){
        return ResponseEntity.ok().build();
    }
}
