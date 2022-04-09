package com.xtsshop.app.db.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
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
    private float price;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private int stock;

    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private Image image;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy(value = "createdAt DESC ")
    private List<PriceHistory> priceHistories;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToMany(mappedBy = "cart")
    private Set<AppUser> users;

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

}
