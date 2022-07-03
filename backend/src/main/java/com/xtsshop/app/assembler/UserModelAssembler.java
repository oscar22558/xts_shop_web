package com.xtsshop.app.assembler;

import com.xtsshop.app.controller.users.UserController;
import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.assembler.models.UserRepresentationModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler extends AbstractModelAssembler<UserRepresentationModel, AppUser>{
    @Override
    public EntityModel<UserRepresentationModel> toModel(AppUser entity) {
        return EntityModel.of(
                UserRepresentationModel.from(entity),
                linkTo(methodOn(UserController.class).one(entity.getUsername())).withSelfRel(),
                linkTo(methodOn(UserController.class).all()).withRel("all")
            );
    }

    @Override
    public CollectionModel<EntityModel<UserRepresentationModel>> toCollectionModel(Iterable<? extends AppUser> entities) {
        return super.toCollectionModel(entities);
    }
}
