package com.xtsshop.app.db.repositories;

import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.entities.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
}
