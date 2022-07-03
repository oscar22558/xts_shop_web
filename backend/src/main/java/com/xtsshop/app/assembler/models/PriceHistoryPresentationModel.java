package com.xtsshop.app.assembler.models;

import com.xtsshop.app.db.entities.PriceHistory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@NoArgsConstructor
@Setter
public class PriceHistoryPresentationModel {
    private Long id;
    private Date createAt;
    private Float value;

    public PriceHistoryPresentationModel(Long id, Date createAt, Float value) {
        this.id = id;
        this.createAt = createAt;
        this.value = value;
    }

    public static PriceHistoryPresentationModel from(PriceHistory entity){
        return new PriceHistoryPresentationModel(
                entity.getId(),
                entity.getCreatedAt(),
                entity.getValue()
        );
    }
}
