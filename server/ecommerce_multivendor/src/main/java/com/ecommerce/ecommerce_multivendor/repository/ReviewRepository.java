package com.ecommerce.ecommerce_multivendor.repository;

import com.ecommerce.ecommerce_multivendor.entity.Product;
import com.ecommerce.ecommerce_multivendor.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findByProductId(Long ProductId);
}
