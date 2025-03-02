package com.ecommerce.ecommerce_multivendor.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Coupon {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private Long id;

    private String code;

    private double discountPrecentage;

    private LocalDate validityStartDate;

    private LocalDate validityEndDate;

    private double minimumOrderValue;

    private boolean isActive = true;

    @ManyToMany(mappedBy = "usedCoupons") // don't create new table go to the user and map with usedCoupon.
    private Set<User> usedByUsers = new HashSet<>();
}
