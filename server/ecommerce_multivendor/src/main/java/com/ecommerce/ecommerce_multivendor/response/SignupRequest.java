package com.ecommerce.ecommerce_multivendor.response;

import lombok.*;

@Getter
@Setter

public class SignupRequest {
    private String email;
    private String fullName;
    private String otp;
}
