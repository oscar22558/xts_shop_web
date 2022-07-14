package com.xtsshop.app.controller.items.images;

import com.xtsshop.app.db.entities.Image;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.entities.builder.ImageBuilder;
import com.xtsshop.app.db.repositories.ImageRepository;
import org.springframework.stereotype.Component;

@Component
public class SaveImage {

    private ImageRepository repository;
    private Item item;

    public SaveImage(
            ImageRepository repository
    ) {
        this.repository = repository;
    }

    public Image create(ImageBuilder builder) {
        return repository.save(builder.setItem(item).build());
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
