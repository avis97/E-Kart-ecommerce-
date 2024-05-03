package com.ecommerce.EKart.Service;

import com.ecommerce.EKart.Dtos.Request.RatingRequest;
import com.ecommerce.EKart.Entitys.Rating;
import com.ecommerce.EKart.Entitys.User;
import com.ecommerce.EKart.Exception.ProductNotFoundException;

import java.util.List;

public interface RatingService{

    Rating createRating(RatingRequest request, User user) throws ProductNotFoundException;
    List<Rating>  getProductRating(int ratingId);

}
