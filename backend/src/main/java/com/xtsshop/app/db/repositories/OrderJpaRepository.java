package com.xtsshop.app.db.repositories;

import com.xtsshop.app.db.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o where o.paymentIntentId=:paymentIntentId")
    Optional<Order> findByPaymentIntentId(String paymentIntentId);

    @Modifying
    @Query("delete from Order o where o.paymentIntentId=:paymentIntentId")
    void deleteByPaymentIntentId(@Param("paymentIntentId") String paymentIntentId);
}
