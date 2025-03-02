package com.ecommerce.ecommerce_multivendor.entity;

import lombok.Data;

@Data
public class BankDetails {
    private String accountNumber;
    private String accountName;
    private String ifscCode;
}
