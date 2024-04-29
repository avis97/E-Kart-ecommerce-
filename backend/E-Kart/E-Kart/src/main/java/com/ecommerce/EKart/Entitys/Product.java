package com.ecommerce.EKart.Entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String details;
    private int price;
    private int discountPrice;
    private int discountPercent;
    private int quantity;
    private String brand;
    private String color;
    private String imgUrl;
    private int numRating;
    private LocalDateTime creationDate;
    @ManyToOne
    @JoinColumn
    private Category category;
    @Embedded
    @ElementCollection
    private Set<Size> sizes=new HashSet<>();
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Rating> ratingList=new ArrayList<>();
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Review> reviewList=new ArrayList<>();
}
