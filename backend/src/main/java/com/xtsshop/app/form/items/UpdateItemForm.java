package com.xtsshop.app.form.items;

import com.xtsshop.app.domain.request.items.UpdateItemRequest;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Nullable;

@Setter
@NoArgsConstructor
public class UpdateItemForm {
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
    private Integer stock;
    @Nullable
    private Long brandId;
    public UpdateItemRequest toRequest(Long id){
        UpdateItemRequest request = new UpdateItemRequest();
        request.setId(id);
        request.setCategoryId(categoryId);
        request.setName(name);
        request.setPrice(price);
        request.setManufacturer(manufacturer);
        request.setCategoryId(categoryId);
        request.setStock(stock);
        request.setBrandId(brandId);
        return request;
    }
}
