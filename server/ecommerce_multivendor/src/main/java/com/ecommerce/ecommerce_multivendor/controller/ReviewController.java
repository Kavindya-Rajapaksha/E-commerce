package com.ecommerce.ecommerce_multivendor.controller;

import com.ecommerce.ecommerce_multivendor.entity.Product;
import com.ecommerce.ecommerce_multivendor.entity.Review;
import com.ecommerce.ecommerce_multivendor.entity.User;
import com.ecommerce.ecommerce_multivendor.request.CreateReviewRequest;
import com.ecommerce.ecommerce_multivendor.response.ApiResponse;
import com.ecommerce.ecommerce_multivendor.service.ProductService;
import com.ecommerce.ecommerce_multivendor.service.ReviewService;
import com.ecommerce.ecommerce_multivendor.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {
    private final ReviewService reviewService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/product/{productId}/review")
    public ResponseEntity<List<Review>> getReviewByProductId(
            @PathVariable Long productId){
        List<Review> reviews = reviewService.getReviewByProductId(productId);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping("/product/{productId}/reviews")
    public ResponseEntity<Review> writeReview(
            @RequestBody CreateReviewRequest request,
            @PathVariable Long productId,
            @RequestHeader("Authorization")String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Product product = productService.findProductById(productId);

        Review review = reviewService.createReview(request,user,product);

        return ResponseEntity.ok(review);
    }

    @PatchMapping("/reviews/{reviewId}")
    public ResponseEntity <Review> updateReview(
            @RequestBody CreateReviewRequest request,
            @PathVariable Long reviewId,
            @RequestHeader ("Authorization")String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        Review review = reviewService.updateReview(
                reviewId,
                request.getReviewText(),
                request.getReviewRating(),
                user.getId()
        );

        return ResponseEntity.ok(review);
    }

    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<ApiResponse> deleteReview(
            @PathVariable Long reviewId,
            @RequestHeader ("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        reviewService.deleteReview(reviewId,user.getId());
        ApiResponse response = new ApiResponse();
        response.setMessage("Review delete successfully");

        return ResponseEntity.ok(response);
    }
}
