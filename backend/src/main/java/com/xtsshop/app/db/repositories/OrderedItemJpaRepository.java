package com.xtsshop.app.db.repositories;

import com.xtsshop.app.db.entities.Order;
import com.xtsshop.app.db.entities.OrderedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderedItemJpaRepository extends JpaRepository<OrderedItem, Long> {
}
