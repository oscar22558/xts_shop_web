package com.xtsshop.app.service.images;

import com.xtsshop.app.db.entities.Image;
import com.xtsshop.app.db.repositories.ImageRepository;
import com.xtsshop.app.request.ImageRequest;
import com.xtsshop.app.service.AbstractService;
import com.xtsshop.app.service.items.ItemsService;
import com.xtsshop.app.service.storage.StorageService;
import com.xtsshop.app.service.storage.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@Service
public class ImagesService extends AbstractService<ImageRequest, Image>{

    Logger logger = LoggerFactory.getLogger(ItemsService.class);
    private StorageService storageService;
    private Util storageServiceUtil;
    public ImagesService(
        ImageRepository repository,
        @Qualifier("ImageStorageService") StorageService storageService,
        Util storageServiceUtil
    ) {
        super(repository);
        this.storageService = storageService;
        this.storageServiceUtil = storageServiceUtil;
    }

    @Override
    public Image create(ImageRequest request) {
        MultipartFile uploadedImg = request.getImage();
        Path imagePath = storageService.store(request.getImage());
        String path = imagePath.toString();
        String fileName = imagePath.getFileName().toString();
        String extension = storageServiceUtil.getExtension(uploadedImg);
        request.setPath(path);
        request.setExtension(extension);
        request.setFileName(fileName);
        return repository.save(request.toEntity());
    }

    @Override
    public Image update(long id, ImageRequest request) {
        Image originalImage = repository.findById(id).orElseThrow();
        storageService.delete(originalImage.getFileName());
        Path path = storageService.store(request.getImage());
        String fileName = path.getFileName().toString();
        String extension = storageServiceUtil.getExtension(request.getImage());
        request.setPath(path.toString());
        request.setFileName(fileName);
        request.setExtension(extension);
        return repository.save(request.toEntity());
    }

    @Override
    public void delete(long id) {
        Image image = repository.getById(id);
        storageService.delete(image.getFileName());
        repository.delete(image);
    }
}
