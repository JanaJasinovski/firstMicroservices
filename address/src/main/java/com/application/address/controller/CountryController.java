package com.application.address.controller;

import com.application.address.dto.CountryDto;
import com.application.address.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/countries")
public class CountryController {
    private final CountryService countryService;

    @GetMapping("/all")
    public List<CountryDto> getAll() {
        return countryService.findAll();
    }
}
