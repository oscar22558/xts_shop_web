package com.xtsshop.app.viewmodels;

import org.springframework.http.ResponseEntity;

public class DeleteRequestViewModel {
    public ResponseEntity<?> getResponse(){
        return ResponseEntity.ok().build();
    }
}
