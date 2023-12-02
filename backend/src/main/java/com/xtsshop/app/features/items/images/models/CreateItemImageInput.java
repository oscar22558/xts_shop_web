package com.xtsshop.app.features.items.images.models;

import com.xtsshop.app.db.entities.Item;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class CreateItemImageInput {
    private MultipartFile image;
    private Item item;

    public CreateItemImageInput(MultipartFile image, Item item) {
        this.image = image;
        this.item = item;
    }
}
