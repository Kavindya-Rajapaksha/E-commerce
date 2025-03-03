package com.ecommerce.ecommerce_multivendor.controller;

import com.ecommerce.ecommerce_multivendor.entity.HomeCategory;
import com.ecommerce.ecommerce_multivendor.entity.HomePage;
import com.ecommerce.ecommerce_multivendor.service.HomeCategoryService;
import com.ecommerce.ecommerce_multivendor.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeCategoryController {
    private  final HomeCategoryService homeCategoryService;
    private final HomeService homeService;

    @PostMapping("/home/categories")
    public ResponseEntity<HomePage>createHomeCategory(
            @RequestBody List<HomeCategory> homeCategoryList){
        List<HomeCategory> categories = homeCategoryService.createCategories(homeCategoryList);
        HomePage homePage = homeService.createHomePageData(categories);
        return new ResponseEntity<>(homePage, HttpStatus.ACCEPTED);
    }

    @GetMapping("/home-category")
    public ResponseEntity<List<HomeCategory>>getHomeCategory(){
        List<HomeCategory> categories = homeCategoryService.getAllHomeCategories();
        return ResponseEntity.ok(categories);
    }

    @PatchMapping("/home-category/{id}")
    public ResponseEntity<HomeCategory> updateHomeCategory(
            @PathVariable Long id,
            @RequestBody HomeCategory homeCategory) throws Exception {
        HomeCategory updatedHomeCategory = homeCategoryService.updateHomeCategory(homeCategory,id);
        return ResponseEntity.ok(updatedHomeCategory);
    }
}
