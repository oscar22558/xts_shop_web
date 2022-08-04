package com.xtsshop.app.controller.users.addresses;

import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.db.entities.Address;
import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.repositories.UserJpaRepository;
import com.xtsshop.app.db.seed.DevelopmentDataSeed;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
@Transactional
public class DeleteAddressTestHelper {
    private DevelopmentDataSeed data;
    private UserJpaRepository userJpaRepository;

    public DeleteAddressTestHelper(DevelopmentDataSeed data, UserJpaRepository userJpaRepository) {
        this.data = data;
        this.userJpaRepository = userJpaRepository;
    }

    public void insertData(){
        data.insertData();
    }

    public Address getAddressToDeleted(){
        AppUser user = userJpaRepository.findUserByUsername("marry123");
        return Optional.ofNullable(user.getAddresses().get(0))
                .orElseThrow(()->new RecordNotFoundException("Address not found."));
    }

    public long getIdOfAddressToDelete(){
        return getAddressToDeleted().getId();
    }

    public int countAddressOfUser(String username){
       return userJpaRepository.findUserByUsername(username).getAddresses().size();
    }
}
