package com.application.payment.service;

import com.application.payment.model.PaymentAttempt;
import com.application.payment.model.PaymentOrder;
import com.application.payment.payload.PurchaseResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface PaymentAttemptService {
    PaymentOrder placeOrder(PaymentOrder purchase, HttpServletRequest httpServletRequest);
    PaymentAttempt createPaymentAttempt(Long userId, boolean success);
    PaymentOrder getPaymentOrder(Long userId);
    PaymentOrder getPaymentOrder(Long purchaseId, Long userId);

    List<PaymentAttempt> getByPayment(PaymentOrder paymentOrder);
}
