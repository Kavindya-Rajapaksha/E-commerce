package com.ecommerce.ecommerce_multivendor.service;

import com.ecommerce.ecommerce_multivendor.entity.HomeCategory;
import com.ecommerce.ecommerce_multivendor.entity.HomePage;

import java.util.List;

public interface HomeService {
    public HomePage createHomePageData(List<HomeCategory> allCategories);
}
