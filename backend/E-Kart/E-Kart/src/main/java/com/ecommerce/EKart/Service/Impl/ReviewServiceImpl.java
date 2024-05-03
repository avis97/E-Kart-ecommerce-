package com.ecommerce.EKart.Service.Impl;

import com.ecommerce.EKart.Dtos.Request.ReviewRequest;
import com.ecommerce.EKart.Entitys.Product;
import com.ecommerce.EKart.Entitys.Review;
import com.ecommerce.EKart.Entitys.User;
import com.ecommerce.EKart.Exception.ProductNotFoundException;
import com.ecommerce.EKart.Repository.ProductRepository;
import com.ecommerce.EKart.Repository.ReviewRepository;
import com.ecommerce.EKart.Service.ProductService;
import com.ecommerce.EKart.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;

    @Override
    public Review createReview(ReviewRequest request, User user) throws ProductNotFoundException {

        Product product=productService.findProductById(request.getProductId());

        Review review=new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReview(request.getReview());
        review.setCreationDate(LocalDateTime.now());

        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReview(int productId) {
        return reviewRepository.getAllProductReview(productId);
    }
}
