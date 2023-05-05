package com.application.order.client;

import com.application.order.dto.CartItemDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

//cart
@FeignClient( name = "cart", url = "http://localhost:8083", path = "cartItems" )
public interface CartItemClient {

    @GetMapping( "/cartItem/userId" )
    List<CartItemDto> getCartItemByUserId(@RequestHeader( HttpHeaders.AUTHORIZATION) String token);
    @DeleteMapping("/cartItem/clear/userId")
    void clearCartItemByUserId(@RequestHeader( HttpHeaders.AUTHORIZATION) String token);
}
