package com.ecommerce.EKart.Dtos.Request;


import com.ecommerce.EKart.Entitys.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingRequest{
    private int productId;
    private double rating;
}
