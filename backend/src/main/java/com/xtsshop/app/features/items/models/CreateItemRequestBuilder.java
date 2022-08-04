package com.xtsshop.app.features.items.models;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;

public class CreateItemRequestBuilder {
    private String name;
    private Float price;
    private String manufacturer;
    private MultipartFile image;
    private Long categoryId;
    private Integer stack;
    private Long brandId;

    public CreateItemRequestBuilder setName(@Nullable String name) {
        this.name = name;
        return this;
    }

    public CreateItemRequestBuilder setPrice(@Nullable Float price) {
        this.price = price;
        return this;
    }

    public CreateItemRequestBuilder setManufacturer(@Nullable String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public CreateItemRequestBuilder setImage(@Nullable MultipartFile image) {
        this.image = image;
        return this;
    }

    public CreateItemRequestBuilder setCategoryId(@Nullable Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public CreateItemRequestBuilder setStack(@Nullable Integer stack) {
        this.stack = stack;
        return this;
    }

    public CreateItemRequestBuilder setBrandId(Long brandId) {
        this.brandId = brandId;
        return this;
    }

    public CreateItemRequest build(){
        return new CreateItemRequest(
            name,
            price,
            manufacturer,
            image,
            categoryId,
            stack,
            brandId
        );
    }
}
