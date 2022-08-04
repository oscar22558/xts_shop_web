package com.xtsshop.app.controller.items.images;

import com.xtsshop.app.controller.items.images.models.UpdateItemImageInput;
import com.xtsshop.app.controller.storage.StorageService;
import com.xtsshop.app.db.entities.Image;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.entities.builder.ImageBuilder;
import com.xtsshop.app.db.repositories.ImageJpaRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UpdateItemImage {

    private StorageService storageService;
    private SaveImage saveImage;
    private SaveImageToStorage saveImageToStorage;
    private ImageJpaRepository imageJpaRepository;
    private UpdateItemImageInput input;
    private Item item;

    public UpdateItemImage(
            @Qualifier("ImageStorageService") StorageService storageService,
            SaveImage saveImage,
            SaveImageToStorage saveImageToStorage,
            ImageJpaRepository imageJpaRepository
    ) {
        this.storageService = storageService;
        this.saveImage = saveImage;
        this.saveImageToStorage = saveImageToStorage;
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
        storageService.delete(image.getFileName());
        imageJpaRepository.delete(image);
    }

    public Image createNewImage(){
        saveImageToStorage.save(input.getNewImage());
        ImageBuilder imageBuilder = saveImageToStorage.configImageBuilder(new ImageBuilder());
        saveImage.setItem(item);
        return saveImage.create(imageBuilder);
    }
}
