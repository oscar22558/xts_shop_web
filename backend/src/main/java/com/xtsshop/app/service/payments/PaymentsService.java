package com.xtsshop.app.service.payments;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.db.entities.payment.Payment;
import com.xtsshop.app.db.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentsService {
    private PaymentRepository paymentRepository;

    public PaymentsService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment get(Long id) throws RecordNotFoundException {
        return paymentRepository.findById(id).orElseThrow(()->new RecordNotFoundException("Payment with id "+id+" not found."));
    }
}
