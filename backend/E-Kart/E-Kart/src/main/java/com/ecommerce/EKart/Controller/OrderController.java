package com.ecommerce.EKart.Controller;

import com.ecommerce.EKart.Entitys.Address;
import com.ecommerce.EKart.Entitys.Order;
import com.ecommerce.EKart.Entitys.User;
import com.ecommerce.EKart.Exception.OrderNotFoundException;
import com.ecommerce.EKart.Exception.UserNotFoundException;
import com.ecommerce.EKart.Service.OrderService;
import com.ecommerce.EKart.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController{
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @PostMapping("/create") // Use a more specific endpoint
    public ResponseEntity<?> createOrder(@RequestBody Address shippingAddress,
                                         @RequestHeader("Authorization") String jwt) {
        try {
            User user = userService.findUserByJwt(jwt.substring(7)); // Assuming JWT starts with "Bearer "
            Order order = orderService.createOrder(user, shippingAddress);
            return ResponseEntity.status(HttpStatus.CREATED).body(order);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
    @GetMapping("/user")
    public ResponseEntity<List<Order>> userOrderHistory(@RequestHeader("Authorization") String jwt) throws UserNotFoundException {
        User user=userService.findUserByJwt(jwt.substring(7));
        List<Order> orders=orderService.usersOrderHistory(user.getId());
        return new ResponseEntity<>(orders,HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable("id") int orderId,
                                               @RequestHeader("Authorization") String jwt) throws UserNotFoundException, OrderNotFoundException {
        User user=userService.findUserByJwt(jwt.substring(7));
        Order order=orderService.findOrderById(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

}
