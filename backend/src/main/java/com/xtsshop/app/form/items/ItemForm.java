package com.xtsshop.app.form.items;

import com.xtsshop.app.domain.request.items.CreateItemRequest;
import com.xtsshop.app.domain.request.items.CreateItemRequestBuilder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;

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
