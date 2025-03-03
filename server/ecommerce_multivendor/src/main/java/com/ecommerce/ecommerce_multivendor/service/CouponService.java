package com.ecommerce.ecommerce_multivendor.service;

import com.ecommerce.ecommerce_multivendor.entity.Cart;
import com.ecommerce.ecommerce_multivendor.entity.Coupon;
import com.ecommerce.ecommerce_multivendor.entity.User;

import java.util.List;

public interface CouponService {
    Cart applyCoupon (String code, double orderValue, User user) throws Exception;
    Cart removeCoupon (String code, User user) throws Exception;
    Coupon findCouponById (Long id) throws Exception;
    Coupon createCoupon (Coupon coupon);
    List<Coupon> findAllCoupons();
    void deleteCoupon(Long couponId) throws Exception;
}
