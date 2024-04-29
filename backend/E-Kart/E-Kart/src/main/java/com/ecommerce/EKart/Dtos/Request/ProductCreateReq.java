package com.ecommerce.EKart.Dtos.Request;


import com.ecommerce.EKart.Entitys.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductCreateReq{
    private String title;
    private String descriptions;
    private int price;
    private int discountPrice;
    private int discountPercent;
    private int quantity;
    private String brandName;
    private String imgUrl;
    private String color;
    private LocalDateTime createTime;
    private Set<Size> sizeSet=new HashSet<>();
    private String firstCategory;
    private String secondCategory;
    private String thirdCategory;

}
