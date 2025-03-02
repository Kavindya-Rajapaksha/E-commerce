package com.ecommerce.ecommerce_multivendor.entity;

import com.ecommerce.ecommerce_multivendor.domain.PaymentStatus;
import lombok.Data;

@Data
public class PaymentDetails {
    private String paymentId;
    private String paymentLinkId;
    private String paymentLinkReference;
    private String paymentLinkStatus;
    private String paymentIdZWSP;
    private PaymentStatus status;
}
