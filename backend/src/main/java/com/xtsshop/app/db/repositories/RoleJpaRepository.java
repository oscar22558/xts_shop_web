package com.xtsshop.app.db.repositories;

import com.xtsshop.app.db.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleJpaRepository extends JpaRepository<Role, Long> {
    @Query(value = "select * from roles r where r.name = ?1 limit 1", nativeQuery = true)
    Role findByName(String name);
}
