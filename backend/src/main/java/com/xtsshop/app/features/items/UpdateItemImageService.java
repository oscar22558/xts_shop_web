package com.xtsshop.app.features.items;

import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.features.items.images.models.UpdateItemImageInput;
import com.xtsshop.app.features.items.images.models.UpdateItemImageRequest;
import com.xtsshop.app.db.entities.Image;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.repositories.ItemJpaRepository;
import com.xtsshop.app.features.items.images.UpdateItemImage;
import org.springframework.stereotype.Service;

@Service
public class UpdateItemImageService {

    private UpdateItemImage updateItemImage;
    private ItemJpaRepository itemJpaRepository;
    private UpdateItemImageRequest request;
    private Item item;

    public UpdateItemImageService(UpdateItemImage updateItemImage, ItemJpaRepository itemJpaRepository) {
        this.updateItemImage = updateItemImage;
        this.itemJpaRepository = itemJpaRepository;
    }

    public Item update(UpdateItemImageRequest request) {
        this.request = request;
        getItem();
        Image image = updateItemImage.update(new UpdateItemImageInput(item, request.getImage()));
        item.setImage(image);
        return item;
    }

    public void getItem(){
        item = itemJpaRepository
                .findById(request.getItemId())
                .orElseThrow(()->new RecordNotFoundException("Item with id "+request.getItemId()+" not found"));
    }
}
