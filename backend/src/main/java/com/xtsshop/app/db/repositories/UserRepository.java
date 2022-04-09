package com.xtsshop.app.db.repositories;

import com.xtsshop.app.db.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    @Query(value = "select * from users u where u.username = ?1", nativeQuery = true)
    AppUser findUserByUsername(String username);
}
