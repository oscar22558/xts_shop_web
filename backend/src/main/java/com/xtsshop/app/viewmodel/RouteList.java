package com.xtsshop.app.viewmodel;

import com.xtsshop.app.controller.categories.CategoriesCRUDController;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Getter
@Setter
@NoArgsConstructor
public class RouteList {
    private String categories =
            linkTo(methodOn(CategoriesCRUDController.class).all()).withRel("categories").getHref();
}
