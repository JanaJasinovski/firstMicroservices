package com.application.address.service;

import com.application.address.dto.AddressDto;
import jakarta.servlet.http.HttpServletRequest;

public interface AddressService {

    void createAddress(String token, AddressDto addressDto, Long userId);

    AddressDto getAddressByUserId(Long userId);
}
