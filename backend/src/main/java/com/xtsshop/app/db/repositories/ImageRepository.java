package com.xtsshop.app.db.repositories;

import com.xtsshop.app.db.entities.Image;
import com.xtsshop.app.db.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> { }
