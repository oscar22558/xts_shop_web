package com.xtsshop.app.db.repositories;

import com.xtsshop.app.db.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "select * from categories where parent_id is null", nativeQuery = true)
    List<Category> findAllTopLevel();

    @Query("select c from Category c where c.name=:name")
    Optional<Category> findFirstCategoryByName(String name);
}
