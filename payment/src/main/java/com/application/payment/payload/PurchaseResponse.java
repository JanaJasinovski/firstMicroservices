package com.application.payment.payload;

import lombok.Data;

@Data
public class PurchaseResponse {
    private final String orderTrackingNumber;
}
