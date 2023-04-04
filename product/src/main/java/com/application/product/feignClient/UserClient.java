package com.application.product.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient( name = "oauth", url = "http://localhost:8081", path = "api/admin")
public interface UserClient {
    @GetMapping( "/{username}" )
    boolean isUserAdmin(@PathVariable String username);

}
