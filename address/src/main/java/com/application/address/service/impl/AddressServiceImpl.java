package com.application.address.service.impl;

import com.application.address.dto.AddressDto;
import com.application.address.model.Address;
import com.application.address.repository.AddressRepository;
import com.application.address.service.AddressService;
import com.application.address.utils.DtoConvert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final DtoConvert dtoConvert;

    @Override
    public void createAddress(String token, AddressDto addressDto, Long userId) {

        Address address = new Address();
        address.setCountry(addressDto.getCountry());
        address.setState(addressDto.getState());
        address.setCity(addressDto.getCity());
        address.setStreet(addressDto.getStreet());
        address.setZipCode(addressDto.getZipCode());
        address.setUserId(userId);

        addressRepository.save(address);
    }

    @Override
    public AddressDto getAddressByUserId(Long userId) {
        return dtoConvert.convertAddressToDto(addressRepository.getAddressByUserId(userId));
    }
}
