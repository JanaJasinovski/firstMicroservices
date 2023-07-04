package com.application.payment.agregator.service;

import com.application.payment.agregator.dto.PaymentRequest;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface PaymentService {
    PaymentIntent createPaymentIntent(PaymentRequest request) throws StripeException;
}
