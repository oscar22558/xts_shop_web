package com.xtsshop.app.db.repositories;

import com.xtsshop.app.db.entities.AppUser;
import com.xtsshop.app.db.entities.QueryResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserJpaRepository extends JpaRepository<AppUser, Long> {
    @Query(value = "select * from users u where u.username = ?1", nativeQuery = true)
    AppUser findUserByUsername(String username);

    @Query(value =
            "select u.id as id, u.username as username, r.name as role from users u " +
            "inner join users_roles ur on ur.user_id=u.id " +
            "inner join roles r on ur.role_id=r.id"
            , nativeQuery = true)
    List<QueryResult> list();

}
