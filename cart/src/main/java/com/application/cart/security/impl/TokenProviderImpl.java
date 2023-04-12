package com.application.cart.security.impl;


import com.application.cart.dto.UserDto;
import com.application.cart.security.TokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class TokenProviderImpl implements TokenProvider {

    public static final String ROLE_ADMIN = "ADMIN";
    @Value( "${app.jwt.secret}" )
    private String JWT_SECRET;

    @Override
    public UserDto extractUser(HttpServletRequest request) {
        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
        String token = getToken(request);
        if (validateToken(token)) {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            List<String> roles = claims.get("roles", List.class);
            Long userId = claims.get("userId", Long.class);
            String username = claims.getSubject();
            return new UserDto(userId, username, roles.get(0));

        } else {
            return null;
        }
    }

    @Override
    public String getToken(HttpServletRequest httpServletRequest) {
        final String bearerToken = httpServletRequest.getHeader("Authorization");
        if (StringUtils.hasLength(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.split(" ")[1].trim();
        }
        return null;
    }

    @Override
    public boolean validateToken(String token) {
        Key key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims != null && !claims.getExpiration().before(new Date());

        } catch (Exception e) {
            return true;
        }
    }
}
