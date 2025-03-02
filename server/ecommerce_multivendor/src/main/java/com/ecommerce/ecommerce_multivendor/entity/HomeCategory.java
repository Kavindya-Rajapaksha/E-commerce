package com.ecommerce.ecommerce_multivendor.entity;

import com.ecommerce.ecommerce_multivendor.domain.HomeCategorySection;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class HomeCategory {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    private String name;

    private String image;

    private String categoryId;

    private HomeCategorySection section;
}
