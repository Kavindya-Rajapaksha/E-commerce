package com.ecommerce.ecommerce_multivendor.service;

import com.ecommerce.ecommerce_multivendor.entity.HomeCategory;

import java.util.List;

public interface HomeCategoryService {
    HomeCategory createHomeCategory(HomeCategory homeCategoryList);
    List<HomeCategory> createCategories(List<HomeCategory> homeCategoryList);
    HomeCategory updateHomeCategory (HomeCategory homeCategory, Long id) throws Exception;
    List<HomeCategory>getAllHomeCategories();
}
