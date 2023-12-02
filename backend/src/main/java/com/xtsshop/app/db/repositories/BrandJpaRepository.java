package com.xtsshop.app.db.repositories;

import com.xtsshop.app.db.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandJpaRepository extends JpaRepository<Brand, Long> {
}
