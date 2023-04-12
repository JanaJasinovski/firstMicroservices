package com.application.oauth.controller;

import com.application.oauth.dto.BearerToken;
import com.application.oauth.dto.LoginDto;
import com.application.oauth.dto.RegisterDto;
import com.application.oauth.dto.UserDto;
import com.application.oauth.model.User;
import com.application.oauth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/api/authentication" )
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping( "/signup" )
    public BearerToken register(@RequestBody RegisterDto registerDto) {
        return authenticationService.register(registerDto);
    }

    @PostMapping("/signin")
    public String login(@RequestBody LoginDto loginDto) {
        return authenticationService.login(loginDto);
    }

}
