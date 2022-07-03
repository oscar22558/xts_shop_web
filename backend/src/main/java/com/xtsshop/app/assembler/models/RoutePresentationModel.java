package com.xtsshop.app.assembler.models;

import com.xtsshop.app.controller.categories.CategoriesController;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
@Setter
@NoArgsConstructor
public class RoutePresentationModel {
    private String categories =
            linkTo(methodOn(CategoriesController.class).all()).withRel("categories").getHref();
}
