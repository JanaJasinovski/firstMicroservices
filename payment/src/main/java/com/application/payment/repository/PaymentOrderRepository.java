package com.application.payment.repository;

import com.application.payment.model.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Long> {
    PaymentOrder findPaymentOrderByCustomerId(Long id);

    PaymentOrder findPaymentOrderByCustomerIdAndId(Long purchaseId, Long userId);
}
