package com.ecommerce.ecommerce_multivendor.repository;

import com.ecommerce.ecommerce_multivendor.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon,Long> {
    Coupon findByCode(String code);
}
