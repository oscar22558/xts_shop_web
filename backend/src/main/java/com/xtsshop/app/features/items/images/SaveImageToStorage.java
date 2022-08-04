package com.xtsshop.app.features.items.images;

import com.xtsshop.app.features.storage.StorageService;
import com.xtsshop.app.db.entities.builder.ImageBuilder;
import com.xtsshop.app.features.storage.Util;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@Component
public class SaveImageToStorage {

    private StorageService storageService;
    private Util storageServiceUtil;

    private String path;
    private String fileName;
    private String extension;

    public SaveImageToStorage(
            @Qualifier("ImageStorageService") StorageService storageService,
            Util storageServiceUtil
    ) {
        this.storageService = storageService;
        this.storageServiceUtil = storageServiceUtil;
    }

    public void save(MultipartFile image) {
        Path imagePath = storageService.store(image);
        path = imagePath.toString();
        fileName = imagePath.getFileName().toString();
        extension = storageServiceUtil.getExtension(image);
    }

    public ImageBuilder configImageBuilder(ImageBuilder imageBuilder){
        return imageBuilder
                .setFileName(fileName)
                .setPath(path)
                .setExtension(extension);
    }
}
