package com.xtsshop.app.features.items;

import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.features.items.images.UpdateImageService;
import com.xtsshop.app.features.items.images.models.UpdateItemImageInput;
import com.xtsshop.app.features.items.images.models.UpdateItemImageRequest;
import com.xtsshop.app.db.entities.Image;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.repositories.ItemJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateItemImageService {

    private UpdateImageService updateImageService;
    private ItemJpaRepository itemJpaRepository;
    private UpdateItemImageRequest request;
    private Item item;

    public UpdateItemImageService(UpdateImageService updateImageService, ItemJpaRepository itemJpaRepository) {
        this.updateImageService = updateImageService;
        this.itemJpaRepository = itemJpaRepository;
    }

    public Item update(UpdateItemImageRequest request) {
        this.request = request;
        getItem();
        Image image = updateImageService.update(new UpdateItemImageInput(item, request.getImage()));
        item.setImage(image);
        return item;
    }

    public void getItem(){
        item = itemJpaRepository
                .findById(request.getItemId())
                .orElseThrow(()->new RecordNotFoundException("Item with id "+request.getItemId()+" not found"));
    }
}
