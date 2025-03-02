package com.ecommerce.ecommerce_multivendor.service;

import com.ecommerce.ecommerce_multivendor.domain.AccountStatus;
import com.ecommerce.ecommerce_multivendor.entity.Seller;
import com.ecommerce.ecommerce_multivendor.exceptions.SellerException;

import java.util.List;

public interface SellerService {
    Seller getSellerProfile(String jwt) throws Exception;
    Seller createSeller(Seller seller) throws Exception;
    Seller getSellerById(Long id) throws SellerException;
    Seller getSellerByEmail (String email) throws Exception;
    List<Seller>getAllSellers(AccountStatus status);
    Seller updateSeller(Long id, Seller seller) throws Exception;
    void deleteSeller (Long id) throws Exception;
    Seller verifyEmail(String email, String otp) throws Exception;
    Seller updateSellerAccountStatus (long sellerId, AccountStatus status) throws Exception;
}
