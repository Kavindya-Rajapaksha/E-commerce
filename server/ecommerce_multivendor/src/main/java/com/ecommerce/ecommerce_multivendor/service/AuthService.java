package com.ecommerce.ecommerce_multivendor.service;

import com.ecommerce.ecommerce_multivendor.domain.USER_ROLE;
import com.ecommerce.ecommerce_multivendor.request.LoginRequest;
import com.ecommerce.ecommerce_multivendor.response.AuthResponse;
import com.ecommerce.ecommerce_multivendor.response.SignupRequest;

public interface AuthService {
    void sentLoginOtp(String email, USER_ROLE role) throws Exception;
    String createUser (SignupRequest req) throws Exception;
    AuthResponse signing(LoginRequest req) throws Exception;
}
