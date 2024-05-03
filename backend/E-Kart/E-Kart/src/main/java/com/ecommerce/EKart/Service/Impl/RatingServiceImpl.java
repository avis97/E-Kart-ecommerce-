package com.ecommerce.EKart.Service.Impl;

import com.ecommerce.EKart.Dtos.Request.RatingRequest;
import com.ecommerce.EKart.Entitys.Product;
import com.ecommerce.EKart.Entitys.Rating;
import com.ecommerce.EKart.Entitys.User;
import com.ecommerce.EKart.Exception.ProductNotFoundException;
import com.ecommerce.EKart.Repository.RatingRepository;
import com.ecommerce.EKart.Service.ProductService;
import com.ecommerce.EKart.Service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService{

    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    ProductService productService;

    @Override
    public Rating createRating(RatingRequest request, User user) throws ProductNotFoundException {
        Product product=productService.findProductById(request.getProductId());
        Rating rating=new Rating();
        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(request.getRating());
        rating.setCreationDate(LocalDateTime.now());
        return ratingRepository.save(rating);

    }

    @Override
    public List<Rating> getProductRating(int productId){
        return ratingRepository.getAllProductRatings(productId);
    }
}
