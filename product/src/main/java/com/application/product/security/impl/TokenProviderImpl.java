package com.application.product.security.impl;

import com.application.product.security.TokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class TokenProviderImpl implements TokenProvider {

    @Value( "${app.jwt.secret}" )
    private String JWT_SECRET;

    @Override
    public Long extractUserId(HttpServletRequest request) {
        String token = getToken(request);
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token);

        Claims body = claimsJws.getBody();
        return Long.parseLong(body.get("userId").toString());

    }

    @Override
    public String extractUsername(HttpServletRequest request) {
        String token = getToken(request);
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token);

        Claims body = claimsJws.getBody();
        return body.getSubject();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
        }
        return false;
    }

    @Override
    public String getToken(HttpServletRequest httpServletRequest) {
        final String bearerToken = httpServletRequest.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

}
