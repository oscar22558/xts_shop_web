package com.xtsshop.app.viewmodel;

import com.xtsshop.app.db.entities.PriceHistory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@NoArgsConstructor
@Setter
public class PriceHistoryViewModel {
    private Long id;
    private Date createAt;
    private Float value;

    public PriceHistoryViewModel(Long id, Date createAt, Float value) {
        this.id = id;
        this.createAt = createAt;
        this.value = value;
    }

    public static PriceHistoryViewModel from(PriceHistory entity){
        return new PriceHistoryViewModel(
                entity.getId(),
                entity.getCreatedAt(),
                entity.getValue()
        );
    }
}
