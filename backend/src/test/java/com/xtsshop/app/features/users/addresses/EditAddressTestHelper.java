package com.xtsshop.app.features.users.addresses;

import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.db.entities.Address;
import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.repositories.UserJpaRepository;
import com.xtsshop.app.db.seed.TestDataSeed;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
@Transactional
public class EditAddressTestHelper {
    private AppUser user;
    private TestDataSeed data;
    private UserJpaRepository userJpaRepository;

    public EditAddressTestHelper(UserJpaRepository userJpaRepository, TestDataSeed data) {
        this.userJpaRepository = userJpaRepository;
        this.data = data;
    }

    public void insertData(){
        data.insertData();
    }

    public Address getAddressToUpdated(){
        user = userJpaRepository.findUserByUsername("marry123");
        return Optional.ofNullable(user.getAddresses().get(0))
                .orElseThrow(()->new RecordNotFoundException("Address not found."));
    }

    public long getIdOfAddressToUpdated(){
        return getAddressToUpdated().getId();
    }

    public String getUpdatedAddressRow1(){
        return getAddressToUpdated().getRow1();
    }
}
