package com.application.cart.feignClient;

import com.application.cart.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient( name = "oauth", url = "http://localhost:8081", path = "api/user")
public interface UserClient {
    @GetMapping("/{username}")
    UserDto findUserByUsername(@PathVariable String username);
}
