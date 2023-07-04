package com.application.payment.agregator.payload;

import lombok.Data;

@Data
public class PurchaseResponse {
    private final String orderTrackingNumber;
}
