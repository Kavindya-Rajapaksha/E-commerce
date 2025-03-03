package com.ecommerce.ecommerce_multivendor.repository;

import com.ecommerce.ecommerce_multivendor.entity.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Long> {
    PaymentOrder findByPaymentLinkId (String paymentId);
}
