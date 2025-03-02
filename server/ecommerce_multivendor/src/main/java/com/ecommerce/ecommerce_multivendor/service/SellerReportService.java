package com.ecommerce.ecommerce_multivendor.service;

import com.ecommerce.ecommerce_multivendor.entity.Seller;
import com.ecommerce.ecommerce_multivendor.entity.SellerReport;

public interface SellerReportService {
    SellerReport getSellerReport (Seller seller);
    SellerReport updateSellerReport (SellerReport sellerReport);
}
