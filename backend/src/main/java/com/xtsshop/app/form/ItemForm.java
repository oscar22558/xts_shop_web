package com.xtsshop.app.form;

import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.request.ItemRequest;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Nullable;

@Setter
@NoArgsConstructor
public class ItemForm implements Form<ItemRequest, Item> {
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
    @Override
    public ItemRequest toRequest(){
        ItemRequest request = new ItemRequest();
        request.setName(name);
        request.setPrice(price);
        request.setManufacturer(manufacturer);
        request.setCategoryId(categoryId);
        return request;
    }
}
