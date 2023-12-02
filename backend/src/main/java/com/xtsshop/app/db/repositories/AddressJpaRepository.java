package com.xtsshop.app.db.repositories;

import com.xtsshop.app.db.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressJpaRepository extends JpaRepository<Address, Long> {
}
