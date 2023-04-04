package com.application.oauth.service;

import com.application.oauth.dto.BearerToken;
import com.application.oauth.dto.LoginDto;
import com.application.oauth.dto.RegisterDto;
import com.application.oauth.model.User;

public interface AuthenticationService {
    String login(LoginDto loginDto);
    User register(RegisterDto registerDto);

}
