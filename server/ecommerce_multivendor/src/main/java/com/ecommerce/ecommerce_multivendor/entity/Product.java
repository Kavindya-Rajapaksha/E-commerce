package com.ecommerce.ecommerce_multivendor.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    private int mrpPrice;

    private int sellingPrice;

    private int discountPercent;

    private int quantity;

    private String color;

    @ElementCollection // Separate table create for images when adding elementcollection
    private List<String> images = new ArrayList<>();

    private int numRating;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Seller seller;

    private LocalDateTime createdAt;

    private String sizes;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Review>reviews = new ArrayList<>();

}
