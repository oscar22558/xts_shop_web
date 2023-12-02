package com.xtsshop.app.db.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class AppUser extends AppEntity{
    @Column(nullable = false, length = 100)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "password_encrypted_at")
    private Date passwordEncryptedAt;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(name = "last_login_at")
    private Date lastLoginAt;

    @Column(length = 20)
    private String phone;


    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name ="role_id", referencedColumnName = "id"
            )
    )
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    @ManyToMany
    @JoinTable(
            name = "carts",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id")
    )
    private Set<Item> cart;

    public AppUser(Date createdAt, Date updatedAt, String username, String password, String email, String phone) {
        super(createdAt, updatedAt);
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public void addItemToCart(Item item){
        cart.add(item);
        item.getUsers().add(this);
    }
    public void removeItemFromCart(Item item){
        cart.remove(item);
        item.getUsers().remove(this);
    }

    public void addRole(Role role){
        this.roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role){
        this.roles.remove(role);
        role.getUsers().remove(this);
    }
}
