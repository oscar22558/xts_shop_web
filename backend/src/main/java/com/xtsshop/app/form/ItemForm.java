package com.xtsshop.app.form;

import com.xtsshop.app.domain.request.ItemRequest;
import com.xtsshop.app.domain.request.builder.ItemRequestBuilder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;

@Setter
@NoArgsConstructor
public class ItemForm{
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
    public ItemRequest toRequest(){
        return new ItemRequestBuilder()
            .setName(name)
            .setPrice(price)
            .setManufacturer(manufacturer)
            .setCategoryId(categoryId)
            .setStack(stack)
            .build();
    }
}
