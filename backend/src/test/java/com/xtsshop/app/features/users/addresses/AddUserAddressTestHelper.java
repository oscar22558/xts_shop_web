package com.xtsshop.app.features.users.addresses;

import com.xtsshop.app.db.repositories.UserJpaRepository;
import com.xtsshop.app.db.seed.DevDataSeed;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Transactional
@Component
public class AddUserAddressTestHelper {
    private DevDataSeed data;
    private UserJpaRepository userJpaRepository;

    public AddUserAddressTestHelper(DevDataSeed data, UserJpaRepository userJpaRepository) {
        this.data = data;
        this.userJpaRepository = userJpaRepository;
    }

    public void insertData(){
        data.insertData();
    }

    public int countUserAddress(String username){
        return userJpaRepository.findUserByUsername(username).getAddresses().size();
    }
}
