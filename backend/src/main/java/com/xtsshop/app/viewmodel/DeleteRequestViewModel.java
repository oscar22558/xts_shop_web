package com.xtsshop.app.viewmodel;

import org.springframework.http.ResponseEntity;

public class DeleteRequestViewModel {
    public ResponseEntity<?> getResponse(){
        return ResponseEntity.ok().build();
    }
}
