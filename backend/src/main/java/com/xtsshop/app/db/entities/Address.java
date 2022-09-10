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

    @Column(length = 100)
    private String district;

    @Column(length = 100)
    private String area;

    @Column(nullable = false, length = 100)
    private String row1;

    @Column(length = 100)
    private String row2;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    public Address(Date createdAt, Date updatedAt, String country, String city, String district, String area, String row1, String row2) {
        super(createdAt, updatedAt);
        this.country = country;
        this.city = city;
        this.district = district;
        this.area = area;
        this.row1 = row1;
        this.row2 = row2;
    }
}
