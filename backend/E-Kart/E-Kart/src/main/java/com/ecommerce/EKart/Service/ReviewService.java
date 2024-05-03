package com.ecommerce.EKart.Service;

import com.ecommerce.EKart.Dtos.Request.ReviewRequest;
import com.ecommerce.EKart.Entitys.Review;
import com.ecommerce.EKart.Entitys.User;
import com.ecommerce.EKart.Exception.ProductNotFoundException;

import java.util.List;

public interface ReviewService{
    Review createReview(ReviewRequest request, User user) throws ProductNotFoundException;
    List<Review> getAllReview(int productId);

}
