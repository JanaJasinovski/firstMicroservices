package com.application.address.controller;

import com.application.address.dto.StateDto;
import com.application.address.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/states")
public class StateController {
    private final StateService stateService;

    @GetMapping("/allStates")
    public List<StateDto> getAllStates() {
        return stateService.getAllStates();
    }

    @GetMapping("/search/findByCountryCode")
    List<StateDto> findByCountryCode(@Param("code") String code){
        return stateService.findByCountryCode(code);
    }

}
