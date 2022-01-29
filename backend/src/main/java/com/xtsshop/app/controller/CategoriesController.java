package com.xtsshop.app.controller;

import com.xtsshop.app.assembler.CategoryModelAssembler;
import com.xtsshop.app.entities.Category;
import com.xtsshop.app.model.CategoryModel;
import com.xtsshop.app.repository.CategoryRepository;
import com.xtsshop.app.request.category.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
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
    @PostMapping("/category")
    public void create(@RequestBody CreateForm form){
        CreateFormAdapter adapter = new CreateFormAdapter(form);
        categoryRepository.save(adapter);
    }

    @PutMapping("/category")
    public void update(@RequestBody UpdateForm form){
        Category original = categoryRepository.getById(form.getId());
        UpdateFormAdapter adapter = new UpdateFormAdapter(form, original);
        categoryRepository.save(adapter);
    }

    @DeleteMapping("/category")
    public void delete(@RequestBody DeleteForm form){
        DeleteFormAdapter adapter = new DeleteFormAdapter(form);
        categoryRepository.delete(adapter);
    }
}
