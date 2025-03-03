package com.ecommerce.ecommerce_multivendor.controller;

import com.ecommerce.ecommerce_multivendor.entity.Product;
import com.ecommerce.ecommerce_multivendor.entity.User;
import com.ecommerce.ecommerce_multivendor.entity.WishList;
import com.ecommerce.ecommerce_multivendor.exceptions.ProductException;
import com.ecommerce.ecommerce_multivendor.service.ProductService;
import com.ecommerce.ecommerce_multivendor.service.UserService;
import com.ecommerce.ecommerce_multivendor.service.WishListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishlist")
public class WishListController {

    private final WishListService wishListService;
    private final UserService userService;
    private final ProductService productService;

   @GetMapping
    public ResponseEntity<WishList> getWishlistByUserId(
            @RequestHeader("Authorization") String jwt) throws Exception {

       User user = userService.findUserByJwtToken(jwt);
       WishList wishList = wishListService.getWishListByUserId(user);

       return ResponseEntity.ok(wishList);
   }

   @PostMapping("/add-product/{productId}")
    public ResponseEntity<WishList> addProductToWishList(
            @PathVariable Long productId,
            @RequestHeader("Authorization")String jwt) throws Exception {

       Product product = productService.findProductById(productId);
       User user = userService.findUserByJwtToken(jwt);
       WishList updatedWishList = wishListService.addProductToWishList(
               user,
               product
       ) ;

       return ResponseEntity.ok(updatedWishList);
   }
}
