package com.xtsshop.app.controller.authentication;

import com.xtsshop.app.controller.authentication.models.AuthRequest;
import com.xtsshop.app.controller.users.models.SpringUser;
import com.xtsshop.app.controller.users.exceptions.UserModelAssembler;
import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.controller.users.models.UserRepresentationModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @GetMapping("/user")
    public EntityModel<UserRepresentationModel> user(){
        SpringUser springUser = userIdentityService.getSpringUser();
        AppUser user = new AppUser();
        user.setUsername(springUser.getUsername());
        user.setEmail(springUser.getEmail());
        user.setPhone(springUser.getPhone());
        return assembler.toModel(user);
    }
}
