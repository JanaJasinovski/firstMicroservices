package com.application.product.security;

import jakarta.servlet.http.HttpServletRequest;

public interface TokenProvider {
    Long extractUserId(HttpServletRequest request);

    String extractUsername(HttpServletRequest request);

    boolean validateToken(String token);

    String getToken(HttpServletRequest httpServletRequest);

}
