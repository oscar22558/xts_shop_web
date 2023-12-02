package com.xtsshop.app.features.authentication;

import com.xtsshop.app.features.authentication.models.AuthRequest;
import com.xtsshop.app.features.users.models.UserModelAssembler;
import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.features.users.models.UserRepresentationModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    private JWTService jwtService;
    private AuthenticationManager authenticationManager;
    private UserIdentityService userIdentityService;
    private UserModelAssembler assembler;

    public AuthController(JWTService jwtService, AuthenticationManager authenticationManager, UserIdentityService userIdentityService, UserModelAssembler assembler) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userIdentityService = userIdentityService;
        this.assembler = assembler;
    }

    @PostMapping("/api/auth")
    public ResponseEntity<Map<String, String>> issueToken(@Valid @RequestBody AuthRequest request) {
        String token = jwtService.generateToken(request, authenticationManager);
        Map<String, String> response = Collections.singletonMap("token", token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/auth/valid")
    public ResponseEntity<Map<String, Object>> validToken() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/auth/user")
    public EntityModel<UserRepresentationModel> user(){
        AppUser user = userIdentityService.getUser();
        return assembler.toModel(user);
    }
}
