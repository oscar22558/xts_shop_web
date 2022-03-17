package com.xtsshop.app.db.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Date;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class AppEntity {

    @Id
    @GeneratedValue
    @Column(unique = true)
    long id;

    @Column(nullable = false, name = "created_at")
    private Date createdAt;

    @Column(nullable = false, name = "updated_at")
    private Date updatedAt;


    public AppEntity(long id) {
        this.id = id;
    }
    public AppEntity(Date createdAt, Date updatedAt){
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public AppEntity(long id, Date createdAt, Date updatedAt){
        this(id);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
   }
}

