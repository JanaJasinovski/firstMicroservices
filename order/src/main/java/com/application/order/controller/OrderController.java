package com.application.order.controller;

import com.application.order.dto.CartItemDto;
import com.application.order.model.Order;
import com.application.order.security.TokenProvider;
import com.application.order.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/getOrders")
    public List<Order> getOrder(HttpServletRequest request) {
        System.out.println(tokenProvider.extractUser(request).getId());
        return orderService.getOrders(tokenProvider.extractUser(request).getId());
    }

    @GetMapping("/getCartItems")
    public List<CartItemDto> getCartItemsFromOrder(@Param("cartId") String cartId, HttpServletRequest request) {
        System.out.println(tokenProvider.extractUser(request).getId());
        return orderService.getAllCartItems(cartId, tokenProvider.extractUser(request).getId());
    }

//    @GetMapping("/all")
//    public ResponseEntity<List<Order>> getOrders() {
//        return ResponseEntity.ok(orderService.getOrders());
//    }
}
