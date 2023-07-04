package com.application.address.dto;

import com.application.address.model.State;
import lombok.Data;

import java.util.List;

@Data
public class CountryDto {
    private Long id;
    private String code;
    private String name;
    private List<State> states;
}
