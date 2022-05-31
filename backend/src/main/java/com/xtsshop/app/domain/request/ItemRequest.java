package com.xtsshop.app.domain.request;

import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.entities.PriceHistory;
import com.xtsshop.app.db.entities.builder.PriceHistoryBuilder;
import com.xtsshop.app.util.DateTimeUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
        Date now = new DateTimeUtil().now();
        Item item = new Item();
        item.setName(name.orElseThrow(NullPointerException::new));
        item.setCreatedAt(now);
        item.setUpdatedAt(now);
        item.setPrice(price.orElseThrow(NullPointerException::new));
        item.setManufacturer(manufacturer.orElseThrow(NullPointerException::new));
        item.setCategory(category);
        item.setStock(stack.orElseThrow(NullPointerException::new));
        PriceHistory priceHistory = new PriceHistoryBuilder()
                .setItem(item)
                .setValue(item.getPrice())
                .build();
        List<PriceHistory> histories = new ArrayList<>();
        histories.add(priceHistory);
        item.setPriceHistories(histories);
        return item;
    }

    public Item update(Item original, Optional<Category> category) {
        Date now = new DateTimeUtil().now();
        original.setName(name.orElse(original.getName()));
        original.setManufacturer(manufacturer.orElse(original.getManufacturer()));
        original.setStock(stack.orElse(original.getStock()));
        original.setCategory(category.orElse(original.getCategory()));
        updatePrice(original);
        if(
            name.isPresent() ||
            isPriceChanged(original) ||
            manufacturer.isPresent() ||
            image.isPresent() ||
            categoryId.isPresent() ||
            stack.isPresent()
        )
            original.setUpdatedAt(now);

        return original;
    }

    private void updatePrice(Item item){
        boolean isPriceChanged = isPriceChanged(item);
        price.ifPresent(value->{
            if(isPriceChanged){
                item.setPrice(value);
                PriceHistory priceHistory = new PriceHistoryBuilder()
                        .setItem(item)
                        .setValue(value)
                        .build();
                item.getPriceHistories().add(priceHistory);
            }
        });
    }
    private boolean isPriceChanged(Item item){
        float latestPrice = item.getLatestPrice();
        return price.flatMap(value->Optional.of(latestPrice != value)).orElse(false);
    }
}
