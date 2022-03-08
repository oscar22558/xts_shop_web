package com.xtsshop.app.db.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category extends AppEntity{

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="parent_id")
    @Nullable
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> subCategories;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;


    public Category(long id) {
        super(id);
    }
    public Category(Date createdAt, String name, Category parent) {
        super(createdAt, null);
        this.name = name;
        this.parent = parent;
    }
}
