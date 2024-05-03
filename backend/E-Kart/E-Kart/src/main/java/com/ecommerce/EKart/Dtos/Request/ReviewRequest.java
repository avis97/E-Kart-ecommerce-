package com.ecommerce.EKart.Dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewRequest{
    private int productId;
    private String review;
}
