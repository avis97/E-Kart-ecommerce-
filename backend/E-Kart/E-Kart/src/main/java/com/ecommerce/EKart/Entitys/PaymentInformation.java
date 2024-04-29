package com.ecommerce.EKart.Entitys;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentInformation{
   private String cardHolderName;
   private String cardNumber;
   private LocalDate expireDate;
   private String cvv;
}
