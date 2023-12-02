package com.xtsshop.app.db.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "price_histories")
public class PriceHistory extends AppEntity{
    @Column(nullable = false)
    private float value;

    @ManyToOne
    @JoinColumn(nullable = false, name = "item_id")
    private Item item;
}
