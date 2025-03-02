package com.ecommerce.ecommerce_multivendor.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL) //when do changes in cart item then changes will behandle in here too.
    private Set<CartItems> cartItems = new HashSet<>();

    private double totalSellingPrice;
    private double totalMrpPrice;

    private int totalItem;

    private int discount;

    private String couponCode;
}
