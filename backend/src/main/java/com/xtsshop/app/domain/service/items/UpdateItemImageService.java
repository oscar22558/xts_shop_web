package com.xtsshop.app.domain.service.items;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.db.entities.Image;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.domain.request.items.images.UpdateItemImageRequest;
import com.xtsshop.app.domain.service.items.images.UpdateItemImage;
import com.xtsshop.app.domain.service.items.images.dto.UpdateItemImageInput;
import org.springframework.stereotype.Service;

@Service
public class UpdateItemImageService {

    private UpdateItemImage updateItemImage;
    private ItemRepository itemRepository;
    private UpdateItemImageRequest request;
    private Item item;

    public UpdateItemImageService(UpdateItemImage updateItemImage, ItemRepository itemRepository) {
        this.updateItemImage = updateItemImage;
        this.itemRepository = itemRepository;
    }

    public Item update(UpdateItemImageRequest request) {
        this.request = request;
        getItem();
        Image image = updateItemImage.update(new UpdateItemImageInput(item, request.getImage()));
        item.setImage(image);
        return item;
    }

    public void getItem(){
        item = itemRepository
                .findById(request.getItemId())
                .orElseThrow(()->new RecordNotFoundException("Item with id "+request.getItemId()+" not found"));
    }
}