package com.xtsshop.app.features.items.images;

import com.xtsshop.app.features.items.images.models.UpdateItemImageInput;
import com.xtsshop.app.features.storage.StorageService;
import com.xtsshop.app.db.entities.Image;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.entities.builder.ImageBuilder;
import com.xtsshop.app.db.repositories.ImageJpaRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UpdateImageService {

    private StorageService storageService;
    private SaveImageService saveImageService;
    private StoreImageService storeImageService;
    private ImageJpaRepository imageJpaRepository;
    private UpdateItemImageInput input;
    private Item item;

    public UpdateImageService(
            @Qualifier("ImageStorageService") StorageService storageService,
            SaveImageService saveImageService,
            StoreImageService storeImageService,
            ImageJpaRepository imageJpaRepository
    ) {
        this.storageService = storageService;
        this.saveImageService = saveImageService;
        this.storeImageService = storeImageService;
        this.imageJpaRepository = imageJpaRepository;
    }

    public Image update(UpdateItemImageInput input) {
        this.input = input;
        item = input.getItem();
        deleteOriginalImage();
        return createNewImage();
    }

    public void deleteOriginalImage() {
        Image image = item.getImage();
        storageService.delete(image.getDescription());
        imageJpaRepository.delete(image);
    }

    public Image createNewImage(){
        storeImageService.store(input.getNewImage());
        ImageBuilder imageBuilder = storeImageService.configImageBuilder(new ImageBuilder());
        saveImageService.setItem(item);
        return saveImageService.create(imageBuilder);
    }
}
