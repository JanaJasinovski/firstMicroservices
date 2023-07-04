package com.example.elasticSearch.security;

import com.example.elasticSearch.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;

public interface TokenProvider {
    UserDto extractUser(HttpServletRequest request);
    String getToken(HttpServletRequest httpServletRequest);
    boolean validateToken(String token);
}
