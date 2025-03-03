package com.ecommerce.ecommerce_multivendor.repository;

import com.ecommerce.ecommerce_multivendor.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishList,Long> {
    WishList findByUserId(Long userId);
}
