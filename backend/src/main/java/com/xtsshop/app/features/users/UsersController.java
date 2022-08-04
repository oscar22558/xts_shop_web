package com.xtsshop.app.features.users;

import com.xtsshop.app.advices.exception.UnAuthenticationException;
import com.xtsshop.app.features.users.exceptions.UserModelAssembler;
import com.xtsshop.app.features.users.models.*;
import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.features.users.models.UserRepresentationModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private UsersService usersService;
    private UserModelAssembler assembler;

    public UsersController(
            UsersService usersService,
            UserModelAssembler assembler
    ) {
        this.usersService = usersService;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<UserRepresentationModel>> all() {
        return assembler.toCollectionModel(usersService.findAll());
    }

    @GetMapping("/{username}")
    public EntityModel<?> one(@PathVariable @NotBlank String username){
        return assembler.toModel(usersService.findUserByUserName(username));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UserCreateForm form) {
        AppUser user = usersService.create(form);
        EntityModel<UserRepresentationModel> userModel = assembler.toModel(user);
        return ResponseEntity
                .created(userModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(user);
    }

    @PutMapping()
    public ResponseEntity<?> update(@Valid @RequestBody UserUpdateForm userUpdateForm) {
        AppUser updatedUser = usersService.update(userUpdateForm);
        EntityModel<UserRepresentationModel> userModel = assembler.toModel(updatedUser);
        return ResponseEntity.ok(userModel);
    }

    @PatchMapping("/password")
    public ResponseEntity<?> updatePassword(@Valid @RequestBody UserUpdatePasswordForm form) throws UnAuthenticationException{
        AppUser user = usersService.updatePassword(form);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> delete(@NotBlank @PathVariable String username){
        usersService.delete(username);
        return ResponseEntity.ok().build();
    }
}
