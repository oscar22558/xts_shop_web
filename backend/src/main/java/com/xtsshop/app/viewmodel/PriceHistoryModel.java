package com.xtsshop.app.viewmodel;

import com.xtsshop.app.db.entities.PriceHistory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GenerationType;
import java.sql.Date;

@Getter
@NoArgsConstructor
@Setter
public class PriceHistoryModel {
    private Long id;
    private Date createAt;
    private Float value;

    public PriceHistoryModel(Long id, Date createAt, Float value) {
        this.id = id;
        this.createAt = createAt;
        this.value = value;
    }

    public static PriceHistoryModel from(PriceHistory entity){
        return new PriceHistoryModel(
                entity.getId(),
                entity.getCreatedAt(),
                entity.getValue()
        );
    }
}
