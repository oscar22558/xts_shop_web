package com.xtsshop.app.features.items.images;

import com.xtsshop.app.features.storage.StorageService;
import com.xtsshop.app.db.entities.builder.ImageBuilder;
import com.xtsshop.app.features.storage.UriPathConverter;
import com.xtsshop.app.features.storage.Util;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@Service
public class StoreImageService {

    private StorageService storageService;

    private UriPathConverter uriPathConverter;
    private Util storageServiceUtil;

    private String uri;
    private String description;
    private String extension;

    public StoreImageService(
            @Qualifier("ImageStorageService") StorageService storageService,
            UriPathConverter uriPathConverter,
            Util storageServiceUtil
    ) {
        this.storageService = storageService;
        this.uriPathConverter = uriPathConverter;
        this.storageServiceUtil = storageServiceUtil;
    }

    public void store(MultipartFile image) {
        Path imagePath = storageService.store(image);
        uri = uriPathConverter.getUri(imagePath);
        description = imagePath.getFileName().toString();
        extension = storageServiceUtil.getExtension(image);
    }

    public ImageBuilder configImageBuilder(ImageBuilder imageBuilder){
        return imageBuilder
                .setDescription(description)
                .setUri(uri)
                .setExtension(extension);
    }
}
