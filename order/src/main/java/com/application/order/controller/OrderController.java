package com.application.order.controller;

import com.application.order.model.Order;
import com.application.order.security.TokenProvider;
import com.application.order.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final TokenProvider tokenProvider;
    private final OrderService orderService;

    @PostMapping("/create")
    public Order createOrder(HttpServletRequest request) {
        String token = "Bearer " + tokenProvider.getToken(request);
        return orderService.createOrder(tokenProvider.extractUser(request).getId(), token);
    }

    @GetMapping("/getOrder")
    public Order getOrder(HttpServletRequest request) {
        String token = "Bearer " + tokenProvider.getToken(request);
        return orderService.getOrder(tokenProvider.extractUser(request).getId());
    }

//    @GetMapping("/all")
//    public ResponseEntity<List<Order>> getOrders() {
//        return ResponseEntity.ok(orderService.getOrders());
//    }
}
