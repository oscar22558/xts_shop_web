package com.xtsshop.app.features.items.images;

import com.xtsshop.app.db.entities.Image;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.entities.builder.ImageBuilder;
import com.xtsshop.app.db.repositories.ImageJpaRepository;
import org.springframework.stereotype.Component;

@Component
public class SaveImageService {

    private ImageJpaRepository repository;
    private Item item;

    public SaveImageService(
            ImageJpaRepository repository
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
