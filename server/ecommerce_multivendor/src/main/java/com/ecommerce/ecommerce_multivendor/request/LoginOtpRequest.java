package com.ecommerce.ecommerce_multivendor.request;

import com.ecommerce.ecommerce_multivendor.domain.USER_ROLE;
import lombok.Data;

@Data
public class LoginOtpRequest {
    private String email;
    private String otp;
    private USER_ROLE role;

}
