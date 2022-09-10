package com.xtsshop.app.db.repositories;

import com.xtsshop.app.db.entities.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceHistoryJpaRepository extends JpaRepository<PriceHistory, Long> {
}
