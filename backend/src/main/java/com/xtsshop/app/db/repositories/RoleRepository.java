package com.xtsshop.app.db.repositories;

import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
