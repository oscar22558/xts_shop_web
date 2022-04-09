package com.xtsshop.app.db.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

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
    private List<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<Address> addresses;

    @OneToMany(mappedBy = "user")
    private List<Coupon> coupons;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    public AppUser(Date createdAt, Date updatedAt, String username, String password, String email, Date lastLoginAt, String phone) {
        super(createdAt, updatedAt);
        this.username = username;
        this.password = password;
        this.email = email;
        this.lastLoginAt = lastLoginAt;
        this.phone = phone;
    }

    public AppUser(String username, String password, String email, String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }
}
