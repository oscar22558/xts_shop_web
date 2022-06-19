package com.xtsshop.app.domain.service.items.images;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.db.entities.Image;
import com.xtsshop.app.db.repositories.ImageRepository;
import com.xtsshop.app.domain.request.items.images.UpdateItemImageRequest;
import com.xtsshop.app.domain.service.storage.StorageService;
import com.xtsshop.app.domain.service.storage.Util;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.List;

@Service
public class ImagesService {
    private StorageService storageService;
    private ImageRepository repository;

    public ImagesService(@Qualifier("ImageStorageService") StorageService storageService, ImageRepository repository) {
        this.storageService = storageService;
        this.repository = repository;
    }

    public List<Image> all(){
        return repository.findAll();
    }
    public Image get(Long id) {
        return repository.findById(id).orElseThrow(()->new RecordNotFoundException("Image of id "+id+" not found"));
    }

    public void delete(Long id){
        Image image = get(id);
        storageService.delete(image.getFileName());
        repository.delete(image);
    }
}
