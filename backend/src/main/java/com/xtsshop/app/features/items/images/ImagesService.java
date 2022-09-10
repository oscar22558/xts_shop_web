package com.xtsshop.app.features.items.images;

import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.db.entities.Image;
import com.xtsshop.app.db.repositories.ImageJpaRepository;
import com.xtsshop.app.features.storage.StorageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagesService {
    private StorageService storageService;
    private ImageJpaRepository repository;

    public ImagesService(@Qualifier("ImageStorageService") StorageService storageService, ImageJpaRepository repository) {
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
