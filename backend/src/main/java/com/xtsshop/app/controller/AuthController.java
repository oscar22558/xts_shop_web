package com.xtsshop.app.controller;

import com.xtsshop.app.request.AuthRequest;
import com.xtsshop.app.service.auth.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    private JWTService jwtService;
    private AuthenticationManager authenticationManager;

    public AuthController(JWTService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> issueToken(@Valid @RequestBody AuthRequest request) {
        String token = jwtService.generateToken(request, authenticationManager);
        Map<String, String> response = Collections.singletonMap("token", token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/parse")
    public ResponseEntity<Map<String, Object>> parseToken(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        Map<String, Object> response = jwtService.parseToken(token);

        return ResponseEntity.ok(response);
    }
}
