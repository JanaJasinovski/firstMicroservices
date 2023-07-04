package com.application.payment.client;

import com.application.payment.dto.AddressDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient( name = "address", url = "http://localhost:8089", path = "address" )
public interface AddressClient {

    @GetMapping("/getAddress")
    AddressDto getAddressByUserId(@RequestHeader( HttpHeaders.AUTHORIZATION) String token, HttpServletRequest request);
}
