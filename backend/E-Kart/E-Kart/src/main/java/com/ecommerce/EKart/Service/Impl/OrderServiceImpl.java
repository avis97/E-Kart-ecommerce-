package com.ecommerce.EKart.Service.Impl;

import com.ecommerce.EKart.Entitys.*;
import com.ecommerce.EKart.Exception.OrderNotFoundException;
import com.ecommerce.EKart.Repository.AddressRepository;
import com.ecommerce.EKart.Repository.OrderItemRepository;
import com.ecommerce.EKart.Repository.OrderRepository;
import com.ecommerce.EKart.Repository.UserRepository;
import com.ecommerce.EKart.Service.CartService;
import com.ecommerce.EKart.Service.OrderItemService;
import com.ecommerce.EKart.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CartService cartService;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    OrderItemRepository orderItemRepository;


    @Override
    public Order createOrder(User user, Address deliveryAddress) {
        // make and save the address with user..
        deliveryAddress.setUser(user);
        Address address=addressRepository.save(deliveryAddress);
        user.getAddressList().add(address);
        userRepository.save(user);
        System.out.println("0k   1-------------");
        Cart cart=cartService.findCartByUserId(user.getId());
        List<OrderItem> orderItems=new ArrayList<>();
        System.out.println("0k   2-------------");
        for(CartItem item: cart.getCartItemList()){
            OrderItem orderItem=new OrderItem();
            orderItem.setPrice(item.getPrice());
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setSize(item.getSize());
            orderItem.setUserId(item.getUserId());
            orderItem.setDiscountPrice(item.getDiscountPrice());

            OrderItem createOrderItem=orderItemRepository.save(orderItem);
            orderItems.add(createOrderItem);
        }
        System.out.println("0k   3-------------");
        Order createOrder=new Order();
        createOrder.setUser(user);
        createOrder.setOrderItemList(orderItems);
        createOrder.setTotalPrice(cart.getTotalPrice());
        createOrder.setTotalDiscountPrice(cart.getTotalDiscountPrice());
        createOrder.setDiscount(cart.getDiscount());
        createOrder.setTotalItem(cart.getTotalItem());
        System.out.println("0k   4-------------");
        createOrder.setAddress(address);
        createOrder.setOrderDateTime(LocalDateTime.now());
        createOrder.setOrderStatus("PENDING");
        createOrder.getPaymentDetails().setPaymentStatus("PENDING");
        createOrder.setCreatedAt(LocalDateTime.now());
        System.out.println("0k   5-------------");
        Order saveOrder=orderRepository.save(createOrder);
//        List<Order> orders=user.getOrderList();
//        orders.add(saveOrder);
        userRepository.save(user);
        System.out.println("0k   6-------------");
        for(OrderItem item:orderItems){
            item.setOrder(saveOrder);
            orderItemRepository.save(item);
        }
        System.out.println("0k   7-------------");
        return saveOrder;
    }

    @Override
    public Order findOrderById(int orderId) throws OrderNotFoundException {
        Order order;
        try{
            order=orderRepository.findById(orderId).get();
        }catch (Exception e){
            throw new OrderNotFoundException("Order Not Found");
        }
        return order;
    }

    @Override
    public List<Order> usersOrderHistory(int userId) {
        User user=userRepository.findById(userId).get();
        List<Order> orders=user.getOrderList();
        return orders;
    }

    @Override
    public Order placedOrder(int orderId) throws OrderNotFoundException {
        Order order=findOrderById(orderId);
        order.setOrderStatus("PLACED");
        return order;
    }

    @Override
    public Order confirmedOrder(int orderId) throws OrderNotFoundException {
        Order order=findOrderById(orderId);
        order.setOrderStatus("CONFIRMED");
        return order;
    }

    @Override
    public Order shippedOrder(int orderId) throws OrderNotFoundException {
        Order order=findOrderById(orderId);
        order.setOrderStatus("SHIPPED");
        return order;
    }

    @Override
    public Order deliveredOrder(int orderId) throws OrderNotFoundException {
        Order order=findOrderById(orderId);
        order.setOrderStatus("DELIVERED");
        return order;
    }

    @Override
    public Order cancelOrder(int orderId) throws OrderNotFoundException {
        Order order=findOrderById(orderId);
        order.setOrderStatus("CANCELLED");
        return order;
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public String deleteOrder(int orderId) throws OrderNotFoundException {
        Order order=findOrderById(orderId);
        orderRepository.delete(order);
        return "Delete Order Done";
    }
}
