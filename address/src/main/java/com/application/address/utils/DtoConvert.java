package com.application.address.utils;

import com.application.address.dto.AddressDto;
import com.application.address.dto.StateDto;
import com.application.address.model.Address;
import com.application.address.model.State;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DtoConvert {
    private final ModelMapper modelMapper;

    public StateDto convertStateToDto(State state) {

        StateDto stateDto = modelMapper.map(state, StateDto.class);
        stateDto.setCountryId(state.getCountry().getId());

        return stateDto;
    }

    public AddressDto convertAddressToDto(Address address) {
        return modelMapper.map(address, AddressDto.class);
    }
}
