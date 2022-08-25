package com.example.realworld.jwt;

import static com.example.realworld.web.exception.ErrorCode.NOT_VALID_TOKEN;

import com.example.realworld.jwt.config.JwtConfig;
import com.example.realworld.jwt.exception.NotValidTokenException;
import com.example.realworld.web.token.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JwtTokenForLoggingIn implements Token {

    private static final String EMAIL = "email";

    private final String tokenValue;

    public JwtTokenForLoggingIn(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public String findEmailByJwtConfig(JwtConfig jwtConfig) {
        Claims claims = findClaimsByJwtConfig(jwtConfig);
        return (String) claims.get(EMAIL);
    }

    private Claims findClaimsByJwtConfig(JwtConfig jwtConfig) {
        Claims claims = null;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(jwtConfig.secretKey())
                    .build()
                    .parseClaimsJws(tokenValue)
                    .getBody();
        } catch (JwtException e) {
            throw new NotValidTokenException(NOT_VALID_TOKEN);
        }
        return claims;
    }

}

