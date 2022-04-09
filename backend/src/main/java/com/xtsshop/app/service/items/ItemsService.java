package com.xtsshop.app.service.items;

import com.xtsshop.app.db.entities.Image;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.request.ImageRequest;
import com.xtsshop.app.request.ItemRequest;
import com.xtsshop.app.service.AbstractService;
import com.xtsshop.app.service.images.ImagesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ItemsService extends AbstractService<ItemRequest, Item> {
    Logger logger = LoggerFactory.getLogger(ItemsService.class);
    private ItemRepository repository;
    private ImagesService imagesService;

    public ItemsService(
        ItemRepository repository,
        ImagesService imagesService
    ){
        super(repository);
        this.repository = repository;
        this.imagesService = imagesService;

    }

    @Override
    public Item create(ItemRequest request) {
        Item item = repository.save(request.toEntity());
        ImageRequest imageForm = new ImageRequest();
        imageForm.setImage(request.getImage());
        imageForm.setItem(item);
        Image image = imagesService.create(imageForm);
        item.setImage(image);
        return repository.save(item);
    }

    @Override
    public Item update(long id, ItemRequest request) {
        Item originalItem = repository.findById(id).orElseThrow();
        MultipartFile uploadedImg = request.getImage();
        Image image = null;
        if (uploadedImg != null) {
            ImageRequest imageRequest = new ImageRequest();
            imageRequest.setImage(uploadedImg);
            imageRequest.setItem(originalItem);
            image = imagesService.update(originalItem.getImage().getId(), imageRequest);
        }
        Item item = request.update(originalItem);
        if(image != null)
            item.setImage(image);
        return repository.save(item);
    }

    @Override
    public void delete(long id) {
        Item item = repository.getById(id);
        imagesService.delete(item.getImage().getId());
        repository.delete(item);
    }
}
