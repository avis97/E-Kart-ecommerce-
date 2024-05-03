package com.ecommerce.EKart.Controller;



import com.ecommerce.EKart.Dtos.Request.ReviewRequest;
import com.ecommerce.EKart.Entitys.Review;
import com.ecommerce.EKart.Entitys.User;
import com.ecommerce.EKart.Exception.ProductNotFoundException;
import com.ecommerce.EKart.Exception.UserNotFoundException;
import com.ecommerce.EKart.Service.ReviewService;
import com.ecommerce.EKart.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController{

    @Autowired
    UserService userService;
    @Autowired
    ReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<Review> createRating(@RequestBody ReviewRequest req,
                                               @RequestHeader("Authorization") String jwt)
            throws UserNotFoundException, ProductNotFoundException {
        User user=userService.findUserByJwt(jwt);
        Review review=reviewService.createReview(req,user);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getProductRatings(@PathVariable int productId,
                                                          @RequestHeader("Authorization") String jwt)
            throws UserNotFoundException {
        User user=userService.findUserByJwt(jwt);
        List<Review> review=reviewService.getAllReview(productId);
        return new ResponseEntity<>(review,HttpStatus.CREATED);
    }

}
