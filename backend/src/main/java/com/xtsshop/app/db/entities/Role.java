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
@Table(name = "roles")
public class Role extends AppEntity{

    @Enumerated(EnumType.STRING)
    private RoleType name;
    @ManyToMany(mappedBy = "roles")
    private Set<AppUser> users;

    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"
            )
    )
    private Set<Privilege> privileges;

    public Role(Date createAt, Date updatedAt, RoleType name){
        super(createAt, updatedAt);
        this.name = name;
    }

    public void addPrivilege(Privilege privilege){
        privileges.add(privilege);
        privilege.getRoles().add(this);
    }
    public void removePrivilege(Privilege privilege){
        privileges.remove(privilege);
        privilege.getRoles().remove(this);
    }
}
