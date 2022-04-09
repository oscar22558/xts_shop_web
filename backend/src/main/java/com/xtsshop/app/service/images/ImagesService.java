package com.xtsshop.app.service.images;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.db.entities.Image;
import com.xtsshop.app.db.repositories.ImageRepository;
import com.xtsshop.app.request.ImageRequest;
import com.xtsshop.app.service.AbstractService;
import com.xtsshop.app.service.items.ItemsService;
import com.xtsshop.app.service.storage.StorageService;
import com.xtsshop.app.service.storage.Util;
import net.bytebuddy.dynamic.scaffold.TypeWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

@Service
public class ImagesService {
    private StorageService storageService;
    private Util storageServiceUtil;
    private ImageRepository repository;
    public ImagesService(
        ImageRepository repository,
        @Qualifier("ImageStorageService") StorageService storageService,
        Util storageServiceUtil
    ) {
        this.repository = repository;
        this.storageService = storageService;
        this.storageServiceUtil = storageServiceUtil;
    }

    public List<Image> all(){
        return repository.findAll();
    }
    public Image get(Long id) throws RecordNotFoundException {
        return repository.findById(id).orElseThrow(()->new RecordNotFoundException("Image of id "+id+" not found"));
    }
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

    public Image update(Long id, ImageRequest request) throws RecordNotFoundException {
        Image originalImage = get(id);
        storageService.delete(originalImage.getFileName());
        Path path = storageService.store(request.getImage());
        String fileName = path.getFileName().toString();
        String extension = storageServiceUtil.getExtension(request.getImage());
        request.setPath(path.toString());
        request.setFileName(fileName);
        request.setExtension(extension);
        return repository.save(request.toEntity());
    }

    public void delete(Long id) throws RecordNotFoundException {
        Image image = get(id);
        storageService.delete(image.getFileName());
        repository.delete(image);
    }
}
