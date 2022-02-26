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

    @Column(nullable = false, name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="parent_id")
    @Nullable
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Category> subCategories;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Item> items;

    public Category(Date createdAt, String name, Category parent) {
        this.createdAt = createdAt;
        this.name = name;
        this.parent = parent;
    }

    public Category(Date createAt, Date updatedAt, String name, Category parent) {
        this.updatedAt = updatedAt;
        this.name = name;
        this.parent = parent;
    }
    public Category(long id) {
        super(id);
    }
    public Category(long id, Date createdAt, String name, Category parent) {
        super(id);
        this.createdAt = createdAt;
        this.name = name;
        this.parent = parent;
    }
}
