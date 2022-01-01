package com.xtsshop.app.repository;

import com.xtsshop.app.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
