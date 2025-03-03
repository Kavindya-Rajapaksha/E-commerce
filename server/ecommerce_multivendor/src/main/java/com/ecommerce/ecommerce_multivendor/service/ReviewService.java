package com.ecommerce.ecommerce_multivendor.service;

import com.ecommerce.ecommerce_multivendor.entity.Product;
import com.ecommerce.ecommerce_multivendor.entity.Review;
import com.ecommerce.ecommerce_multivendor.entity.User;
import com.ecommerce.ecommerce_multivendor.request.CreateReviewRequest;

import java.util.List;

public interface ReviewService {
    Review createReview (CreateReviewRequest req,
                         User user,
                         Product product);
    List<Review> getReviewByProductId(Long productId);
    Review updateReview (Long reviewId, String reviewText, double rating, Long userId) throws Exception;
    void deleteReview(Long reviewId, Long userId) throws Exception;
    Review getReviewById(Long reviewId) throws Exception;
}
