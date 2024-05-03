package com.ecommerce.EKart.Entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String size;
    private int quantity;
    private int price;
    private int discountPrice;
    private int userId;
    private LocalDateTime deliveryDate;
    @ManyToOne
    @JoinColumn
    private Order order;
    @ManyToOne
    private Product product;

}
