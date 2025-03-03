package com.ecommerce.ecommerce_multivendor.service;

import com.ecommerce.ecommerce_multivendor.entity.Product;
import com.ecommerce.ecommerce_multivendor.entity.User;
import com.ecommerce.ecommerce_multivendor.entity.WishList;

public interface WishListService {
    WishList createWishList(User user);
    WishList getWishListByUserId(User user);
    WishList addProductToWishList(User user, Product product);
}
