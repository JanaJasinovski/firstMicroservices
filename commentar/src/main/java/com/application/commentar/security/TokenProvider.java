package com.application.commentar.security;

import com.application.commentar.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;

public interface TokenProvider {
    UserDto extractUser(HttpServletRequest request);
    String getToken(HttpServletRequest httpServletRequest);
    boolean validateToken(String token);
}
