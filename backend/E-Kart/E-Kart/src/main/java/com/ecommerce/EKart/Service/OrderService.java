package com.ecommerce.EKart.Service;

import com.ecommerce.EKart.Entitys.Address;
import com.ecommerce.EKart.Entitys.Order;
import com.ecommerce.EKart.Entitys.User;
import com.ecommerce.EKart.Exception.OrderNotFoundException;

import java.util.List;

public interface OrderService{

    Order createOrder(User user, Address deliveryAddress);
    Order findOrderById(int orderId) throws OrderNotFoundException;
    List<Order> usersOrderHistory(int userId);
    Order placedOrder(int orderId) throws OrderNotFoundException;
    Order confirmedOrder(int orderId) throws OrderNotFoundException;
    Order shippedOrder(int orderId) throws OrderNotFoundException;
    Order deliveredOrder(int orderId) throws OrderNotFoundException;
    Order cancelOrder(int orderId) throws OrderNotFoundException;
    List<Order> getAllOrder();
    String deleteOrder(int orderId) throws OrderNotFoundException;
}
