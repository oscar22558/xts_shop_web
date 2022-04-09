package com.xtsshop.app.request;

import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.db.entities.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import java.sql.Date;
import java.util.Calendar;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class ItemRequest {

    private Optional<String> name;
    private Optional<Float> price;
    private Optional<String> manufacturer;
    Optional<MultipartFile> image;
    private Optional<Long> categoryId;
    private Optional<Integer> stack;

    public Item toEntity(Category category) {
        long now = Calendar.getInstance().getTimeInMillis();
        Item item = new Item();
        item.setName(name.orElseThrow(NullPointerException::new));
        item.setCreatedAt(new Date(now));
        item.setUpdatedAt(new Date(now));
        item.setPrice(price.orElseThrow(NullPointerException::new));
        item.setManufacturer(manufacturer.orElseThrow(NullPointerException::new));
        item.setCategory(category);
        item.setStock(stack.orElseThrow(NullPointerException::new));
        return item;
    }

    public Item update(Item original, Optional<Category> category) {
        original.setName(name.orElse(original.getName()));
        original.setPrice(price.orElse(original.getPrice()));
        original.setManufacturer(manufacturer.orElse(original.getManufacturer()));
        original.setStock(stack.orElse(original.getStock()));
        original.setCategory(category.orElse(original.getCategory()));
        return original;
    }

}
