package com.xtsshop.app.controller.users.exceptions;

import com.xtsshop.app.AbstractModelAssembler;
import com.xtsshop.app.controller.users.UsersController;
import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.controller.users.models.UserRepresentationModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler extends AbstractModelAssembler<UserRepresentationModel, AppUser> {
    @Override
    public EntityModel<UserRepresentationModel> toModel(AppUser entity) {
        return EntityModel.of(
                new UserRepresentationModel(entity),
                WebMvcLinkBuilder.linkTo(methodOn(UsersController.class).one(entity.getUsername())).withSelfRel(),
                linkTo(methodOn(UsersController.class).all()).withRel("all")
            );
    }

    @Override
    public CollectionModel<EntityModel<UserRepresentationModel>> toCollectionModel(Iterable<? extends AppUser> entities) {
        return super.toCollectionModel(entities);
    }
}
