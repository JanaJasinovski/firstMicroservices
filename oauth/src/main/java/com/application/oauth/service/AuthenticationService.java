package com.application.oauth.service;

import com.application.oauth.dto.BearerToken;
import com.application.oauth.dto.LoginDto;
import com.application.oauth.dto.RegisterDto;

public interface AuthenticationService {
    String login(LoginDto loginDto);
    BearerToken register(RegisterDto registerDto);

}
