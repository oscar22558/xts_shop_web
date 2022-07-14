package com.xtsshop.app.controller.items.images.models;

import com.xtsshop.app.db.entities.Item;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class UpdateItemImageInput {
    private Item item;
    private MultipartFile newImage;

    public UpdateItemImageInput(Item item, MultipartFile newImage) {
        this.item = item;
        this.newImage = newImage;
    }
}
