package com.xtsshop.app.assembler;

import com.xtsshop.app.controller.items.ItemsController;
import com.xtsshop.app.controller.users.UserController;
import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.viewmodel.UserViewModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler extends AbstractModelAssembler<UserViewModel, AppUser>{
    @Override
    public EntityModel<UserViewModel> toModel(AppUser entity) {
        return EntityModel.of(
                UserViewModel.from(entity),
                linkTo(methodOn(UserController.class).one(entity.getUsername())).withSelfRel(),
                linkTo(methodOn(UserController.class).all()).withRel("all")
            );
    }

    @Override
    public CollectionModel<EntityModel<UserViewModel>> toCollectionModel(Iterable<? extends AppUser> entities) {
        return super.toCollectionModel(entities);
    }
}
