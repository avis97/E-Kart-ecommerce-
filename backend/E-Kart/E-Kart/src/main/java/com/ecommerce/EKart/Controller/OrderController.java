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
    @PostMapping("/")
    public ResponseEntity createOrder(@RequestBody Address shippingAddress,
                                             @RequestHeader("Authorization") String jwt) throws UserNotFoundException {
        System.out.println("Hello");
        User user=userService.findUserByJwt(jwt.substring(7));
        System.out.println("hello");
        Order order=orderService.createOrder(user,shippingAddress);
       // System.out.println(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
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
