package com.xtsshop.app.features.users.addresses;

import com.xtsshop.app.db.repositories.UserJpaRepository;
import com.xtsshop.app.db.seed.TestDataSeed;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Transactional
@Component
public class AddUserAddressTestHelper {
    private TestDataSeed data;
    private UserJpaRepository userJpaRepository;

    public AddUserAddressTestHelper(TestDataSeed data, UserJpaRepository userJpaRepository) {
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
