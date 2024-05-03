package com.ecommerce.EKart.Repository;

import com.ecommerce.EKart.Entitys.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer>{

}
