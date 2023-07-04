package com.application.payment.agregator.client;

import com.application.payment.agregator.payload.PurchaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient( name = "payment", url = "http://localhost:8089", path = "payment" )
public interface PaymentClient {

    @GetMapping("/getPurchase/{purchaseId}")
    PaymentOrderDto getPurchase(@RequestHeader( HttpHeaders.AUTHORIZATION) String token, @PathVariable Long purchaseId, HttpServletRequest httpServletRequest);
}
