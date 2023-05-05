package com.application.order.security;

import com.application.order.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;

public interface TokenProvider {
    UserDto extractUser(HttpServletRequest request);
    String getToken(HttpServletRequest httpServletRequest);
    boolean validateToken(String token);
    UserDto extractUserFromToken(String token);

}
