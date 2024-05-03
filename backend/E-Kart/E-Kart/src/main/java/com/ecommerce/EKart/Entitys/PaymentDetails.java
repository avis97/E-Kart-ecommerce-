package com.ecommerce.EKart.Entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetails{

    private String paymentMethod;
    private String paymentStatus;
    private String paymentId;
    private String rezorpayPaymentLinkId;
    private String rezorpayPaymentLinkReferenceId;
    private String rezorpayPaymentStatus;
    private String rezorpayPaymentId;
}
