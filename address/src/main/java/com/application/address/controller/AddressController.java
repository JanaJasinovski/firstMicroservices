package com.application.address.controller;

import com.application.address.dto.AddressDto;
import com.application.address.security.TokenProvider;
import com.application.address.service.AddressService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/addresses")
public class AddressController {
    private final AddressService addressService;
    private final TokenProvider tokenProvider;

    @PostMapping("/create")
    public void createAddress(@RequestBody AddressDto addressDto, HttpServletRequest request) {
        String token = "Bearer " + tokenProvider.getToken(request);
        addressService.createAddress(token, addressDto, tokenProvider.extractUser(request).getId());
    }

    @GetMapping("/getAddress")
    public AddressDto getAddressByUserId(HttpServletRequest request) {
        return addressService.getAddressByUserId(tokenProvider.extractUser(request).getId());
    }
}
