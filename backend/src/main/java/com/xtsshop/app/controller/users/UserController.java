package com.xtsshop.app.controller.users;

import com.xtsshop.app.advices.exception.UnAuthenticationException;
import com.xtsshop.app.controller.authentication.AuthenticationService;
import com.xtsshop.app.controller.authentication.UserIdentityService;
import com.xtsshop.app.controller.users.exceptions.UserModelAssembler;
import com.xtsshop.app.controller.users.models.*;
import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.controller.users.models.UserRepresentationModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/users")
public class UserController{
    private UsersCRUDService usersCRUDService;
    private UserModelAssembler assembler;
    private AuthenticationService authenticationService;

    public UserController(
            UsersCRUDService usersCRUDService,
            UserModelAssembler assembler,
            AuthenticationService authenticationService
    ) {
        this.usersCRUDService = usersCRUDService;
        this.assembler = assembler;
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public CollectionModel<EntityModel<UserRepresentationModel>> all() {
        return assembler.toCollectionModel(usersCRUDService.findAll());
    }

    @GetMapping("/{username}")
    public EntityModel<?> one(@PathVariable @NotBlank String username){
        return assembler.toModel(usersCRUDService.findUserByUserName(username));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UserCreateForm form) {
        EntityModel<UserRepresentationModel> user = assembler.toModel(usersCRUDService.create(form.toRequest()));
        return ResponseEntity
                .created(user.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(user);
    }

    @PatchMapping("/username")
    public ResponseEntity<?> updateUsername(@Valid @RequestBody UserUpdateUsernameForm form) throws UnAuthenticationException{
        UserDetails userDetails = authenticationService.confirmPassword(form.getPassword());
        AppUser user = usersCRUDService.updateUsername(userDetails.getUsername(), form.getUsername());
        EntityModel<UserRepresentationModel> model = assembler.toModel(user);
        return ResponseEntity.ok(model);
    }

    @PatchMapping("/password")
    public ResponseEntity<?> updatePassword(@Valid @RequestBody UserUpdatePasswordForm form) throws UnAuthenticationException{
        UserDetails userDetails = authenticationService.confirmPassword(form.getOldPassword());
        UserRequest request = new UserRequest();
        request.setUsername(userDetails.getUsername());
        request.setPassword(form.getPassword());
        AppUser updatedUser = usersCRUDService.update(request);
        EntityModel<UserRepresentationModel> model = assembler.toModel(updatedUser);
        return ResponseEntity.ok(model);
    }

    @PatchMapping("/email")
    public ResponseEntity<?> updateEmail(@Valid @RequestBody UserUpdateEmailForm form) throws UnAuthenticationException {
        UserDetails userDetails = authenticationService.confirmPassword(form.getPassword());
        UserRequest request = new UserRequest();
        request.setUsername(userDetails.getUsername());
        request.setEmail(form.getEmail());
        AppUser updatedUser = usersCRUDService.update(request);
        EntityModel<UserRepresentationModel> model = assembler.toModel(updatedUser);
        return ResponseEntity.ok(model);
    }

    @PatchMapping("/phone")
    public ResponseEntity<?> updatePhone(@Valid @RequestBody UserUpdatePhoneForm form) throws UnAuthenticationException{
        UserDetails user = authenticationService.confirmPassword(form.getPassword());
        UserRequest request = new UserRequest();
        request.setUsername(user.getUsername());
        request.setPhone(form.getPhone());
        AppUser updatedUser = usersCRUDService.update(request);
        EntityModel<UserRepresentationModel> model = assembler.toModel(updatedUser);
        return ResponseEntity.ok(model);
    }
    @DeleteMapping("/{username}")
    public ResponseEntity<?> delete(@NotBlank @PathVariable String username){
        UserRequest request = new UserRequest();
        request.setUsername(username);
        usersCRUDService.delete(request);
        return ResponseEntity.ok().build();
    }
}
