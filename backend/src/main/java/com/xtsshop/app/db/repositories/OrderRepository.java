package com.xtsshop.app.db.repositories;

import com.xtsshop.app.db.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
