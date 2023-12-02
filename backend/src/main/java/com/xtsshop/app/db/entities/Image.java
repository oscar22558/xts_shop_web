package com.xtsshop.app.db.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "images")
public class Image extends AppEntity {
    @Column(name = "uri")
    private String uri;

    @Column(name = "description")
    private String description;

    @Column(name = "extension")
    private String extension;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id", nullable = false, referencedColumnName = "id")
    private Item item;

    public Image(long id){
        super(id);
    }

    public Image(Date createdAt, Date updatedAt, String uri, String description, String extension, Item item){
        super(createdAt, updatedAt);
        this.uri = uri;
        this.description = description;
        this.extension = extension;
        this.item = item;
    }

}
