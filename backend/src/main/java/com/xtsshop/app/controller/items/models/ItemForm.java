package com.xtsshop.app.controller.items.models;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@NoArgsConstructor
public class ItemForm{
    private String name;
    private Float price;
    private String manufacturer;
    private MultipartFile image;
    private Long categoryId;
    private Integer stack;
    private Long brandId;
    public CreateItemRequest toRequest(){
        return new CreateItemRequestBuilder()
            .setName(name)
            .setPrice(price)
            .setManufacturer(manufacturer)
            .setCategoryId(categoryId)
            .setStack(stack)
            .setBrandId(brandId)
            .build();
    }
}
