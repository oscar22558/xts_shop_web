package com.xtsshop.app.db.entities;

import com.xtsshop.app.viewmodel.PriceHistoryModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "items")
public class Item extends AppEntity{
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Min(0)
    private float price;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private int stock;

    @OneToOne(mappedBy = "item", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Image image;

    @OneToMany(mappedBy = "item", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy(value = "createdAt DESC ")
    private List<PriceHistory> priceHistories;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToMany(mappedBy = "cart")
    private Set<AppUser> users;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    public Item(long id) {
        super(id);
    }

    public Item(Date createdAt, Date updatedAt, String name, float price, String manufacturer, Category category, int stock) {
        super(createdAt, updatedAt);
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
        this.category = category;
        this.stock = stock;
    }

    public float getLatestPrice(){
        return getLatestPriceHistory().flatMap(priceHistory -> Optional.of(priceHistory.getValue())).orElse(0f);
    }

    public Optional<PriceHistory> getLatestPriceHistory(){
        int index = priceHistories.size() - 1;
        return Optional.ofNullable(
                index <= -1 ? null : priceHistories.get(index)
        );
    }
}
