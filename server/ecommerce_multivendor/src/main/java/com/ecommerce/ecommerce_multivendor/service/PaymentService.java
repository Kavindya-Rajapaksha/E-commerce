package com.ecommerce.ecommerce_multivendor.service;

import com.ecommerce.ecommerce_multivendor.entity.Order;
import com.ecommerce.ecommerce_multivendor.entity.PaymentOrder;
import com.ecommerce.ecommerce_multivendor.entity.User;
import com.stripe.exception.StripeException;

import java.util.Set;

public interface PaymentService {
    PaymentOrder createOrder(User user, Set<Order> orders);
    PaymentOrder getPaymentOrderById(Long orderId) throws Exception;
    PaymentOrder getPaymentOrderByPaymentId(String orderId) throws Exception;
    Boolean ProceedPaymentOrder(PaymentOrder paymentOrder,
                                String paymentId,
                                String paymentLinkId) throws StripeException;

    String createStripePaymentLink(User user, Long amount, Long orderId) throws StripeException;
}
