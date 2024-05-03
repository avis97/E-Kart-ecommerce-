package com.ecommerce.EKart.Service.Impl;

import com.ecommerce.EKart.Entitys.OrderItem;
import com.ecommerce.EKart.Repository.OrderItemRepository;
import com.ecommerce.EKart.Service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Service
public class OrderItemServiceImpl implements OrderItemService{

    @Autowired
    OrderItemRepository orderItemRepository;
    public OrderItem createOrderItem(OrderItem orderItem){
        return orderItemRepository.save(orderItem);
    }
}
