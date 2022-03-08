package com.xtsshop.app.db.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

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

    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Image image;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchasedItem> purchasedItems;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Item(Date createdAt, String name, float price, String manufacturer, Category category) {
        super(createdAt, null);
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
        this.category = category;
    }
    public Item(Date createdAt, Date updatedAt, String name, float price, String manufacturer, Category category) {
        super(createdAt, updatedAt);
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
        this.category = category;
    }
    public Item(long id){
        super(id);
    }

}
