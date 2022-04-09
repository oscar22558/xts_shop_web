package com.xtsshop.app.db.repositories;

import com.xtsshop.app.db.entities.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
