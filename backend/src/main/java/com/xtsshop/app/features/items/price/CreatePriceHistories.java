package com.xtsshop.app.features.items.price;

import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.entities.PriceHistory;
import com.xtsshop.app.db.entities.builder.PriceHistoryBuilder;
import com.xtsshop.app.db.repositories.PriceHistoryJpaRepository;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class CreatePriceHistories {
    private Item item;
    private List<PriceHistory> histories;
    private PriceHistoryJpaRepository priceHistoryJpaRepository;

    public CreatePriceHistories(PriceHistoryJpaRepository priceHistoryJpaRepository) {
        histories = new ArrayList<>();
        this.priceHistoryJpaRepository = priceHistoryJpaRepository;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<PriceHistory> addPrice(float price){
        PriceHistory priceHistory = new PriceHistoryBuilder()
                .setItem(item)
                .setValue(price)
                .build();
        histories.add(priceHistory);
        return histories;
    }

    public List<PriceHistory> clearList(){
        histories = new ArrayList<>();
        return histories;
    }

    public List<PriceHistory> save(){
        return priceHistoryJpaRepository.saveAll(histories);
    }

}
