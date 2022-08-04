package com.xtsshop.app.db.repositories;

import com.xtsshop.app.db.entities.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeJpaRepository extends JpaRepository<Privilege, Long> {
}
