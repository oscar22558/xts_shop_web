package com.xtsshop.app.domain.request.builder;

import com.xtsshop.app.domain.request.ItemRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import java.util.Optional;

public class ItemRequestBuilder {
    @Nullable
    private String name;
    @Nullable
    private Float price;
    @Nullable
    private String manufacturer;
    @Nullable
    MultipartFile image;
    @Nullable
    private Long categoryId;
    @Nullable
    private Integer stack;

    public ItemRequestBuilder setName(@Nullable String name) {
        this.name = name;
        return this;
    }

    public ItemRequestBuilder setPrice(@Nullable Float price) {
        this.price = price;
        return this;
    }

    public ItemRequestBuilder setManufacturer(@Nullable String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public ItemRequestBuilder setImage(@Nullable MultipartFile image) {
        this.image = image;
        return this;
    }

    public ItemRequestBuilder setCategoryId(@Nullable Long categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public ItemRequestBuilder setStack(@Nullable Integer stack) {
        this.stack = stack;
        return this;
    }
    public ItemRequest build(){
        ItemRequest request = new ItemRequest();
        request.setName(Optional.ofNullable(name));
        request.setPrice(Optional.ofNullable(price));
        request.setManufacturer(Optional.ofNullable(manufacturer));
        request.setImage(Optional.ofNullable(image));
        request.setCategoryId(Optional.ofNullable(categoryId));
        request.setStack(Optional.ofNullable(stack));
        return request;
    }
}
