package com.ecommerce.ecommerce_multivendor.service.impl;

import com.ecommerce.ecommerce_multivendor.domain.PaymentOrderStatus;
import com.ecommerce.ecommerce_multivendor.domain.PaymentStatus;
import com.ecommerce.ecommerce_multivendor.entity.Order;
import com.ecommerce.ecommerce_multivendor.entity.PaymentOrder;
import com.ecommerce.ecommerce_multivendor.entity.User;
import com.ecommerce.ecommerce_multivendor.repository.OrderRepository;
import com.ecommerce.ecommerce_multivendor.repository.PaymentOrderRepository;
import com.ecommerce.ecommerce_multivendor.service.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentOrderRepository paymentOrderRepository;
    private OrderRepository orderRepository;
    @Value("${stripe.secret.key}")
    private String apiKey;

    @Override
    public PaymentOrder createOrder(User user, Set<Order> orders) {
        Long amount = orders.stream().mapToLong(Order::getTotalSellingPrice).sum();

        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setAmount(amount);
        paymentOrder.setUser(user);
        paymentOrder.setOrders(orders);

        return paymentOrderRepository.save(paymentOrder);
    }

    @Override
    public PaymentOrder getPaymentOrderById(Long orderId) throws Exception {

        return paymentOrderRepository.findById(orderId).orElseThrow(()->
                new Exception("Payment Order Not Found"));
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentId(String orderId) throws Exception {
        PaymentOrder paymentOrder = paymentOrderRepository.findByPaymentLinkId(orderId);

        if(paymentOrder==null){
            throw new Exception("Payment order not found with the provided payment link Id - "+orderId);
        }
        return paymentOrder;
    }

    @Override
    public Boolean ProceedPaymentOrder(PaymentOrder paymentOrder,
                                       String paymentId,
                                       String paymentLinkId) throws StripeException {

        if (paymentOrder.getStatus().equals(PaymentOrderStatus.PENDING)) {
            Stripe.apiKey = apiKey;

            PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentId);
            String status = paymentIntent.getStatus();

            if ("succeeded".equals(status)) {
                List<Order> orders = (List<Order>) paymentOrder.getOrders();
                for (Order order : orders) {
                    order.setPaymentStatus(PaymentStatus.COMPLETED);
                    orderRepository.save(order);
                }
                paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
                paymentOrderRepository.save(paymentOrder);
                return true;
            } else {
                paymentOrder.setStatus(PaymentOrderStatus.FAILED);
                paymentOrderRepository.save(paymentOrder);
                return false;
            }
        }
        return false;
    }


    @Override
    public String createStripePaymentLink(User user, Long amount, Long orderId) throws StripeException {
        Stripe.apiKey = apiKey;
        SessionCreateParams sessionCreateParams= SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:3000/payment-success/"+orderId)
                .setCancelUrl("http://localhost:3000/payment-cancel/")
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("usd")
                                .setUnitAmount(amount*100)
                                .setProductData(
                                        SessionCreateParams.LineItem.PriceData.ProductData
                                                .builder().setName("Kavindya Rajapaksha")
                                                .build()
                                ).build()
                        ).build()
                ).build();
        Session session=Session.create(sessionCreateParams);

        return session.getUrl(); //using this url user can make payment
    }
}
