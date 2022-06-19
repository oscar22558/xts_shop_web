package com.xtsshop.app.domain.service.items.images;

import com.xtsshop.app.db.entities.Image;
import com.xtsshop.app.db.entities.builder.ImageBuilder;
import com.xtsshop.app.domain.service.items.images.dto.CreateItemImageInput;
import org.springframework.stereotype.Component;

@Component
public class CreateItemImage {

    private SaveImage saveImage;
    private SaveImageToStorage saveImageToStorage;

    public CreateItemImage(SaveImage saveImage, SaveImageToStorage saveImageToStorage) {
        this.saveImage = saveImage;
        this.saveImageToStorage = saveImageToStorage;
    }

    public Image create(CreateItemImageInput input) {
        saveImageToStorage.save(input.getImage());
        ImageBuilder builder = saveImageToStorage.configImageBuilder(new ImageBuilder());
        saveImage.setItem(input.getItem());
        return saveImage.create(builder);
    }
}
