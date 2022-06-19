package com.xtsshop.app.db.entities.builder;

import com.xtsshop.app.db.entities.AppEntity;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.entities.PriceHistory;
import com.xtsshop.app.util.DateTimeUtil;
import lombok.NoArgsConstructor;
import java.sql.Date;

@NoArgsConstructor
public class PriceHistoryBuilder extends AppEntity {
    private float value;
    private Item item;

    public PriceHistoryBuilder setValue(float value) {
        this.value = value;
        return this;
    }

    public PriceHistoryBuilder setItem(Item item) {
        this.item = item;
        return this;
    }
    public PriceHistory build(){
        Date now = new DateTimeUtil().now();
        PriceHistory priceHistory = new PriceHistory();
        priceHistory.setItem(item);
        priceHistory.setValue(value);
        priceHistory.setCreatedAt(now);
        priceHistory.setUpdatedAt(now);
        return priceHistory;
    }
}
