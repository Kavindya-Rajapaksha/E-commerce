package com.ecommerce.ecommerce_multivendor.entity;

import com.ecommerce.ecommerce_multivendor.domain.PaymentMethod;
import com.ecommerce.ecommerce_multivendor.domain.PaymentOrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class PaymentOrder {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    private Long amount;

    private PaymentOrderStatus status = PaymentOrderStatus.PENDING;

    private PaymentMethod paymentMethod;

    private String paymentLinkId;

    @ManyToOne
    private User user;

    @OneToMany // if try pen and book from seperate order then need to one to many
    private Set<Order> orders = new HashSet<>();
}
