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

    @Column(nullable = false, name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private float price;

    @Column(nullable = false)
    private String manufacturer;

    @Column(name = "img_url", nullable = false)
    private String imgUrl;

    @OneToMany(mappedBy = "item", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<PurchasedItem> purchasedItems;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Item(Date createdAt, Date updatedAt, String name, float price, String manufacturer, String imgUrl, Category category) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
        this.imgUrl = imgUrl;
        this.category = category;
    }

}
