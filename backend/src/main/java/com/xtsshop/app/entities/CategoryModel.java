package com.xtsshop.app.entities;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryModel {
    private long id;

    private Date created_at;

    private Date updated_at;

    private String name;

    private List<CategoryModel> subCategories;

    private List<Item> items;

    public CategoryModel(){}

    public CategoryModel(long id, Date created_at, Date updated_at, String name, List<CategoryModel> subCategories, List<Item> items) {
        this.id = id;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.name = name;
        this.subCategories = subCategories;
        this.items = items;
    }

    public CategoryModel(Date created_at, String name) {
        this.created_at = created_at;
        this.name = name;
    }

    public static CategoryModel from(Category categoryEntity){
        List<CategoryModel> subModels = categoryEntity.getSubCategories().stream().map(CategoryModel::from).collect(Collectors.toList());

        return new CategoryModel(
                categoryEntity.getId(),
                categoryEntity.getCreatedAt(),
                categoryEntity.getUpdatedAt(),
                categoryEntity.getName(),
                subModels,
                categoryEntity.getItems()
        );
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryModel> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<CategoryModel> subCategories) {
        this.subCategories = subCategories;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}