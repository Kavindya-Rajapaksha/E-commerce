package com.ecommerce.ecommerce_multivendor.service;

import com.ecommerce.ecommerce_multivendor.entity.Cart;
import com.ecommerce.ecommerce_multivendor.entity.CartItems;
import com.ecommerce.ecommerce_multivendor.entity.Product;
import com.ecommerce.ecommerce_multivendor.entity.User;

public interface CartService {
    public CartItems addCartItems(
            User user,
            Product product,
            String size,
            int quantity
    );
    public Cart findUserCart(User user);
}
