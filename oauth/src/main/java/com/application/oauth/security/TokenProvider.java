package com.application.oauth.security;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface TokenProvider {
    String extractUsername(String token);

    String generateToken(String email, List<String> roles);

    boolean validateToken(String token);

    String getToken(HttpServletRequest httpServletRequest);
}
