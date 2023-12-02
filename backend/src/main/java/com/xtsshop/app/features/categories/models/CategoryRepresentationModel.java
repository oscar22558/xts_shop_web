package com.xtsshop.app.features.categories.models;

import com.xtsshop.app.AbstractRepresentationModel;
import com.xtsshop.app.db.entities.Category;
import org.springframework.hateoas.EntityModel;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryRepresentationModel implements AbstractRepresentationModel {
    private Category categoryEntity;
    private CategoryModelAssembler assembler;

    public CategoryRepresentationModel(Category categoryEntity, CategoryModelAssembler categoryModelAssembler){
        this.categoryEntity = categoryEntity;
        this.assembler = categoryModelAssembler;
    }

    public long getId(){
        return categoryEntity.getId();
    }

    public String getName(){
        return categoryEntity.getName();
    }

    public List<EntityModel<CategoryRepresentationModel>> getSubCategories(){
        return categoryEntity.getSubCategories()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
    }
}