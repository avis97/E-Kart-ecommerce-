package com.ecommerce.EKart.Dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddItemRequest{
    private int productId;
    private String size;
    private int quantity;
    private int price;

}
