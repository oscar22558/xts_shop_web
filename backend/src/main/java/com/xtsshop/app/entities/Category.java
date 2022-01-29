package com.xtsshop.app.entities;

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
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> subCategories;

    @OneToMany(mappedBy = "category")
    private List<Item> items;

    public Category(long id, Date createdAt, Date updatedAt, String name, Category parent) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
        this.parent = parent;
    }

    public Category(long id, Date createdAt, Date updatedAt, String name, Category parent, List<Category> subCategories, List<Item> items) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.name = name;
        this.parent = parent;
        this.subCategories = subCategories;
        this.items = items;
    }

    public Category(Date createdAt, String name, Category parent) {
        this.createdAt = createdAt;
        this.name = name;
        this.parent = parent;
    }
}
