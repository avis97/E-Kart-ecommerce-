package com.ecommerce.EKart.Controller;

import com.ecommerce.EKart.Entitys.CartItem;
import com.ecommerce.EKart.Entitys.User;
import com.ecommerce.EKart.Exception.UserNotFoundException;
import com.ecommerce.EKart.Service.CartItemService;
import com.ecommerce.EKart.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart_item")
public class CartItemController{

    @Autowired
    CartItemService cartItemService;
    @Autowired
    UserService userService;

    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity removeCartItem(@PathVariable int cartItemId,
                                         @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwt(jwt.substring(7));
        cartItemService.removeCartItem(user.getId(),cartItemId );
        return new ResponseEntity("Item remove done from cart", HttpStatus.ACCEPTED);
    }
    @PutMapping("/update/{cartItemId}")
    public ResponseEntity updateCartItemFromCart(@PathVariable("cartItemId") int cartItemId,@RequestBody CartItem qu,
                                                 @RequestHeader("Authorization") String jwt) {
        System.out.println("Hello");
        try {
            // Validate JWT and get user
            User user = userService.findUserByJwt(jwt.substring(7));
            System.out.println(user.getFirstName());

            // Validate input parameters


            // Update cart item
           CartItem cartItem= cartItemService.updateCartItem(cartItemId, qu.getQuantity());

            return new ResponseEntity(cartItem, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            // Return error response if user not found
            return new ResponseEntity("User not found", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            // Handle other exceptions
            return new ResponseEntity("Error updating cart item: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
