package com.ecommerce.EKart.Entitys;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cart{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int totalItem;
    private double totalPrice;
    private int totalDiscountPrice;
    private int discount;
    @OneToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    private List<CartItem> cartItemList=new ArrayList<>();

}
