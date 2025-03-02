package com.ecommerce.ecommerce_multivendor.service;

import com.ecommerce.ecommerce_multivendor.entity.User;

public interface UserService {
    User findUserByJwtToken (String jwt) throws Exception;
    User findUserByEmail(String email) throws Exception;
}