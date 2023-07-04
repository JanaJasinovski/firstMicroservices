package com.application.payment.client;

import com.application.payment.dto.OrderDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient( name = "order", url = "http://localhost:8084", path = "order" )
public interface OrderClient {

    @GetMapping("/getOrders")
    List<OrderDto> getOrder(@RequestHeader( HttpHeaders.AUTHORIZATION) String token, HttpServletRequest request);
}
