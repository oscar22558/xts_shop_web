package com.xtsshop.app.features.orders.data;

import com.xtsshop.app.db.entities.Address;
import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.entities.Role;
import com.xtsshop.app.db.entities.RoleType;
import com.xtsshop.app.db.repositories.AddressJpaRepository;
import com.xtsshop.app.db.repositories.RoleJpaRepository;
import com.xtsshop.app.db.repositories.UserJpaRepository;
import com.xtsshop.app.helpers.DateTimeHelper;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@TestComponent
@Transactional
public class FakeNewUserDataSet {
    private RoleJpaRepository roleJpaRepository;
    private AddressJpaRepository addressJpaRepository;
    private UserJpaRepository userJpaRepository;

    private String username;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private Date now = new DateTimeHelper().now();
    private AppUser user;

    public FakeNewUserDataSet(RoleJpaRepository roleJpaRepository, AddressJpaRepository addressJpaRepository, UserJpaRepository userJpaRepository) {
        this.roleJpaRepository = roleJpaRepository;
        this.addressJpaRepository = addressJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    public FakeNewUserDataSet setUsername(String username) {
        this.username = username;
        return this;
    }

    public void insertData(){
        user = getUser();
        user.setRoles(getUserRoleSet());
        user.setAddresses(new ArrayList<>());
        user.setOrders(new ArrayList<>());
        user = userJpaRepository.save(user);
        addAddressListForUser();
    }

    protected String getUsername(){
        return "mario123";
    }

    protected AppUser getUser(){
        String username = getUsername();
       return new AppUser(now, now, username, passwordEncoder.encode("123"), "marioy123@xts-shop.com", "28735601");
    }

    protected RoleType getUserRoleType(){
       return RoleType.ROLE_USER;
    }

    private Set<Role> getUserRoleSet(){
        Role role = roleJpaRepository.findByName(getUserRoleType().name());
        return Set.of(role);
    }

    private void addAddressListForUser(){
        List<Address> addresses = getUserAddressList();
        AppUser finalUser = user;
        addresses.forEach(address -> address.setUser(finalUser));
        addresses = addressJpaRepository.saveAll(addresses);
        user.getAddresses().addAll(addresses);
    }

    private List<Address> getUserAddressList(){
        Address address = getUserAddress();
        List<Address> addresses = new ArrayList<>();
        addresses.add(address);
        return addresses;
    }

    protected Address getUserAddress(){
        return new Address(now, now, "China", "Hong Kong", "Hong Kong", "Central and Western", "HKU MB166", null);
    }


}
