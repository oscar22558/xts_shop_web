package com.xtsshop.app.db.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "privileges")
public class Privilege extends AppEntity{

    @Enumerated(EnumType.STRING)
    private PrivilegeType name;
    @ManyToMany(mappedBy = "privileges")
    private Set<Role> roles;

    public Privilege(Date createdAt, Date updatedAt, PrivilegeType name) {
        super(createdAt, updatedAt);
        this.name = name;
    }
}
