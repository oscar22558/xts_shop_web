package com.xtsshop.app.features.items.images;

import com.xtsshop.app.features.items.images.models.CreateItemImageInput;
import com.xtsshop.app.db.entities.Image;
import com.xtsshop.app.db.entities.builder.ImageBuilder;
import org.springframework.stereotype.Component;

@Component
public class CreateImageService {

    private SaveImageService saveImageService;
    private StoreImageService storeImageService;

    public CreateImageService(SaveImageService saveImageService, StoreImageService storeImageService) {
        this.saveImageService = saveImageService;
        this.storeImageService = storeImageService;
    }

    public Image create(CreateItemImageInput input) {
        storeImageService.store(input.getImage());
        ImageBuilder builder = storeImageService.configImageBuilder(new ImageBuilder());
        saveImageService.setItem(input.getItem());
        return saveImageService.create(builder);
    }
}
