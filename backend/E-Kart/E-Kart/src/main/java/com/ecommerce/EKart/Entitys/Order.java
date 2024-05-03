package com.ecommerce.EKart.Entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;
import java.util.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Orders")
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String orderId;
    private double totalPrice;
    private double totalDiscountPrice;
    private int discount;
    private String orderStatus;
    private int totalItem;
    @ManyToOne
    private User user;
    private LocalDateTime orderDateTime;
    private LocalDateTime createdAt;
    private LocalDateTime deliveryDate;
    @OneToOne
    private Address address;
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderItem> orderItemList=new ArrayList<>();
    @Embedded
    private PaymentDetails paymentDetails=new PaymentDetails();

}
