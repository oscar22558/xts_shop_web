package com.xtsshop.app.db.repositories;

import com.xtsshop.app.db.entities.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceHistoryRepository extends JpaRepository<PriceHistory, Long> {
}
