package com.xtsshop.app.controller;

import com.xtsshop.app.assembler.CategoryModelAssembler;
import com.xtsshop.app.entities.CategoryModel;
import com.xtsshop.app.repository.CategoryRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CategoriesController {

    private final CategoryRepository categoryRepository;
    private final CategoryModelAssembler categoryModelAssembler;
    public CategoriesController(CategoryRepository categoryRepository, CategoryModelAssembler modelAssembler){
        this.categoryModelAssembler = modelAssembler;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/category/{id}")
    public EntityModel<CategoryModel> one(@PathVariable long id){
        return categoryModelAssembler.toModel(categoryRepository.findById(id).orElseThrow());
    }
    @GetMapping("/categories")
    public CollectionModel<EntityModel<CategoryModel>> all(){
        List<EntityModel<CategoryModel>> categories = categoryRepository.findAll().stream().map(categoryModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(categories, linkTo(methodOn(CategoriesController.class).all()).withSelfRel());
    }
}
