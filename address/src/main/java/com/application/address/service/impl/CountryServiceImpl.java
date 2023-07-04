package com.application.address.service.impl;

import com.application.address.dto.CountryDto;
import com.application.address.model.Country;
import com.application.address.repository.CountryRepository;
import com.application.address.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CountryDto> findAll() {
        List<Country> countries = countryRepository.findAll();
        return countries.stream()
                .map(country -> {
                    CountryDto countryDto = new CountryDto();
                    countryDto.setId(country.getId());
                    countryDto.setCode(country.getCode());
                    countryDto.setName(country.getName());
                    countryDto.setStates(country.getStates());
                    return countryDto;
                })
                .collect(Collectors.toList());
    }
}
