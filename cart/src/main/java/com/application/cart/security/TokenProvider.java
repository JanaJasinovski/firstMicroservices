package com.application.cart.security;

import com.application.cart.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;

public interface TokenProvider {
    UserDto extractUser(HttpServletRequest request);
    String getToken(HttpServletRequest httpServletRequest);
    boolean validateToken(String token);
    UserDto extractUserFromToken(String token);
}
