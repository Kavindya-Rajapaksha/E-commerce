package com.ecommerce.ecommerce_multivendor.service.impl;

import com.ecommerce.ecommerce_multivendor.entity.Product;
import com.ecommerce.ecommerce_multivendor.entity.Review;
import com.ecommerce.ecommerce_multivendor.entity.User;
import com.ecommerce.ecommerce_multivendor.repository.ReviewRepository;
import com.ecommerce.ecommerce_multivendor.request.CreateReviewRequest;
import com.ecommerce.ecommerce_multivendor.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public Review createReview(CreateReviewRequest req, User user, Product product) {

        Review review = new Review();
        review.setUser(user);
        review.setReviewText(req.getReviewText());
        review.setProduct(product);
        review.setRating(req.getReviewRating());
        review.setProductImages(req.getProductImages());

        product.getReviews().add(review);

        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getReviewByProductId(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    @Override
    public Review updateReview(Long reviewId, String reviewText, double rating, Long userId) throws Exception {
        Review review = getReviewById(reviewId);
        if(review.getUser().getId().equals(userId)){
            review.setReviewText(reviewText);
            review.setRating(rating);
            return reviewRepository.save(review);
        }
        throw new Exception("You can not update this review.");
    }

    @Override
    public void deleteReview(Long reviewId, Long userId) throws Exception {
        Review review= getReviewById(reviewId);
        if(!review.getUser().getId().equals(reviewId)){
            throw new Exception("You can not delete this review");
        }
    }

    @Override
    public Review getReviewById(Long reviewId) throws Exception {
        return reviewRepository.findById(reviewId).orElseThrow(()->
                new Exception("Review not found"));
    }
}
