package com.ecommerce.EKart.Entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Incubating;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String size;
    private int quantity;
    private int price;
    private int discountPrice;
    private int userId;
    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Cart cart;
    @ManyToOne
    private Product product;

}
