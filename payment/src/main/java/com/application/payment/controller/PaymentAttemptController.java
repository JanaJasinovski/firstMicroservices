package com.application.payment.controller;

import com.application.payment.model.PaymentOrder;
import com.application.payment.payload.PurchaseResponse;
import com.application.payment.security.TokenProvider;
import com.application.payment.service.PaymentAttemptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentAttemptController {
    private final PaymentAttemptService paymentAttemptService;
    private final TokenProvider tokenProvider;

    @PostMapping("/purchase/{success}")
    public PurchaseResponse createPurchase(@RequestBody PaymentOrder purchase, HttpServletRequest httpServletRequest, @PathVariable boolean success) {
        paymentAttemptService.createPaymentAttempt(tokenProvider.extractUser(httpServletRequest).getId(), success);
        PaymentOrder paymentOrder = paymentAttemptService.placeOrder(purchase, httpServletRequest);
        return new PurchaseResponse(paymentOrder.getOrderTrackingNumber());
    }

    @GetMapping("/getPurchase/{purchaseId}")
    public PaymentOrder getPurchase(@PathVariable Long purchaseId, HttpServletRequest httpServletRequest) {
        return paymentAttemptService.getPaymentOrder(purchaseId, tokenProvider.extractUser(httpServletRequest).getId());
    }
}
