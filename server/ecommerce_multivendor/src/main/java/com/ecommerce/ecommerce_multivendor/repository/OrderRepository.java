package com.ecommerce.ecommerce_multivendor.repository;

import com.ecommerce.ecommerce_multivendor.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order>findByUserId(Long userId);
    List<Order>findBySellerId(Long sellerId);
}
