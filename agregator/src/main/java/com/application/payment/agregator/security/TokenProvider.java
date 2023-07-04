package com.application.payment.agregator.security;

import com.application.payment.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;

public interface TokenProvider {
    UserDto extractUser(HttpServletRequest request);
    String getToken(HttpServletRequest httpServletRequest);
    boolean validateToken(String token);
}
