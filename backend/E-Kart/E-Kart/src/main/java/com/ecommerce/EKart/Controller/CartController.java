package com.ecommerce.EKart.Controller;

import com.ecommerce.EKart.Dtos.Request.AddItemRequest;
import com.ecommerce.EKart.Entitys.Cart;
import com.ecommerce.EKart.Entitys.User;
import com.ecommerce.EKart.Exception.ProductNotFoundException;
import com.ecommerce.EKart.Exception.UserNotFoundException;
import com.ecommerce.EKart.Service.CartService;
import com.ecommerce.EKart.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/cart")
public class CartController{
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;

    @GetMapping("/")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws UserNotFoundException{
        String JwtNew=jwt.substring(7);
        User user=userService.findUserByJwt(JwtNew);
        Cart cart=cartService.findCartByUserId(user.getId());
        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }
    @PutMapping("/add")
    public ResponseEntity addItemToCart(@RequestBody AddItemRequest request,
                                        @RequestHeader("Authorization") String jwt) throws UserNotFoundException, ProductNotFoundException {
        String JwtNew=jwt.substring(7);
        User user=userService.findUserByJwt(JwtNew);
        cartService.addCartItem(user.getId(),request);

        return new ResponseEntity("Item added to cart",HttpStatus.ACCEPTED);

    }
}
