package com.application.address.service;

import com.application.address.dto.StateDto;

import java.util.List;

public interface StateService {
    List<StateDto> getAllStates();
    List<StateDto> findByCountryCode(String code);

}
