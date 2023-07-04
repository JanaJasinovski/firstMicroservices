package com.application.address.service;

import com.application.address.dto.CountryDto;

import java.util.List;

public interface CountryService {
     List<CountryDto> findAll();
}
