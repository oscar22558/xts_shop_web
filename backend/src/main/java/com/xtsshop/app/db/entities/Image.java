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
    @Column(name = "path")
    private String path;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "extension")
    private String extension;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id", nullable = false, referencedColumnName = "id")
    private Item item;

    public Image(long id){
        super(id);
    }

    public Image(Date createdAt, Date updatedAt, String path, String fileName, String extension, Item item){
        super(createdAt, updatedAt);
        this.path = path;
        this.fileName = fileName;
        this.extension = extension;
        this.item = item;
    }

}
