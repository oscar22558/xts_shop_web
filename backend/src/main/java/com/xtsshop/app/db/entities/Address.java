package com.xtsshop.app.db.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "addresses")
public class Address extends AppEntity{
    @Column(nullable = false, length = 100)
    private String country;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false, length = 100)
    private String row1;

    @Column(length = 100)
    private String row2;

    @Column(length = 100)
    private String row3;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @OneToMany(mappedBy = "shippingAddress")
    private List<Order> orders;


    public Address(Date createdAt, Date updatedAt, String country, String city, String row1, String row2, String row3, AppUser user) {
        super(createdAt, updatedAt);
        this.country = country;
        this.city = city;
        this.row1 = row1;
        this.row2 = row2;
        this.row3 = row3;
        this.user = user;
    }

}
