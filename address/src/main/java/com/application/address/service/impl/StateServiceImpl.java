package com.application.address.service.impl;

import com.application.address.dto.StateDto;
import com.application.address.model.State;
import com.application.address.repository.StateRepository;
import com.application.address.service.StateService;
import com.application.address.utils.DtoConvert;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StateServiceImpl implements StateService {
    private final StateRepository stateRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StateDto> getAllStates() {
        List<State> states = stateRepository.findAll();
        return states.stream()
                .map(state -> modelMapper.map(state, StateDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<StateDto> findByCountryCode(String code) {
        List<State> states = stateRepository.findByCountryCode(code);
        return states.stream()
                .map(state -> modelMapper.map(state, StateDto.class))
                .collect(Collectors.toList());
    }
}
