package com.xtsshop.app.db.repositories;

import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.db.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> { }
