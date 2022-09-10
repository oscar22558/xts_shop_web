package com.xtsshop.app.db.repositories;

import com.xtsshop.app.db.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageJpaRepository extends JpaRepository<Image, Long> { }
