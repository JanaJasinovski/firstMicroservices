package com.application.order.client;

import com.application.order.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient( name = "user", url = "http://localhost:8081", path = "api/admin")
public interface UserClient {
    @GetMapping("/userById/{id}")
    UserDto getUserById(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable Long id);
}
