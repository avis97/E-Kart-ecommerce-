package com.ecommerce.EKart.Controller;

import com.ecommerce.EKart.Dtos.Request.RatingRequest;
import com.ecommerce.EKart.Entitys.Rating;
import com.ecommerce.EKart.Entitys.User;
import com.ecommerce.EKart.Exception.ProductNotFoundException;
import com.ecommerce.EKart.Exception.UserNotFoundException;
import com.ecommerce.EKart.Service.RatingService;
import com.ecommerce.EKart.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RatingController{
    @Autowired
    UserService userService;
    @Autowired
    RatingService ratingService;

    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(@RequestBody RatingRequest req,
                                               @RequestHeader("Authorization") String jwt) throws UserNotFoundException, ProductNotFoundException {
        User user=userService.findUserByJwt(jwt);
        Rating rating=ratingService.createRating(req,user);
        return new ResponseEntity<>(rating, HttpStatus.CREATED);
    }
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Rating>> getProductRatings(@PathVariable int productId,
                                                          @RequestHeader("Authorization") String jwt) throws UserNotFoundException {
        User user=userService.findUserByJwt(jwt);
        List<Rating> ratings=ratingService.getProductRating(productId);
        return new ResponseEntity<>(ratings,HttpStatus.CREATED);
    }
}
