package com.ecommerce.EKart.Controller;

import com.ecommerce.EKart.Entitys.User;
import com.ecommerce.EKart.Exception.UserNotFoundException;
import com.ecommerce.EKart.Service.CartItemService;
import com.ecommerce.EKart.Service.Impl.CartItemServiceImpl;
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
    public ResponseEntity removeCartItem(@PathVariable int cartItemId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwt(jwt.substring(7));
        cartItemService.removeCartItem(user.getId(),cartItemId );
        return new ResponseEntity("Item remove done from cart", HttpStatus.ACCEPTED);
    }

}
