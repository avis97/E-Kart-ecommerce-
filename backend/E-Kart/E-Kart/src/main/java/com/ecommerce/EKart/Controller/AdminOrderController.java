package com.ecommerce.EKart.Controller;

import com.ecommerce.EKart.Entitys.Order;
import com.ecommerce.EKart.Exception.OrderNotFoundException;
import com.ecommerce.EKart.Service.OrderService;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/order")
public class AdminOrderController{

    @Autowired
    OrderService orderService;
    @GetMapping("/")
    public ResponseEntity<List<Order>>  getAllOrderHandler(){
        List<Order> orders=orderService.getAllOrder();
        return new ResponseEntity<List<Order>>(orders, HttpStatus.ACCEPTED);
    }
    @PutMapping("/{orderId}/confirmed")
    public ResponseEntity<Order> confirmedOrderHandler(@PathVariable int orderId,
                                                       @RequestHeader("Authorization") String jwt) throws OrderNotFoundException {
        Order order=orderService.confirmedOrder(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }
    @PutMapping("/{orderId}/shipped")
    public ResponseEntity<Order> shippedOrderHandler(@PathVariable int orderId,
                                                       @RequestHeader("Authorization") String jwt) throws OrderNotFoundException {
        Order order=orderService.shippedOrder(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @PutMapping("/{orderId}/deliver")
    public ResponseEntity<Order> deliveredOrderHandler(@PathVariable int orderId,
                                                     @RequestHeader("Authorization") String jwt) throws OrderNotFoundException {
        Order order=orderService.deliveredOrder(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }
    @PutMapping("/{orderId}/ship")
    public ResponseEntity<Order> cancelOrderHandler(@PathVariable int orderId,
                                                     @RequestHeader("Authorization") String jwt) throws OrderNotFoundException {
        Order order=orderService.cancelOrder(orderId);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }
    @DeleteMapping("/{orderId}/delete")
    public ResponseEntity deleteOrderById(@PathVariable int orderId) throws OrderNotFoundException {
        orderService.deleteOrder(orderId);
        return new ResponseEntity("Order Delete Successfully",HttpStatus.ACCEPTED);
    }
}
