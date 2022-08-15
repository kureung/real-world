package com.example.realworld.jwt;

import static java.util.Objects.isNull;

import com.example.realworld.jwt.exception.NotValidTokenException;
import com.example.realworld.web.exception.ErrorCode;
import com.example.realworld.web.token.TokenParser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenParser implements TokenParser {

    private final String secretKey;

    public JwtTokenParser(@Value("${jwt.token.secret-key}") String secretKey) {
        this.secretKey = secretKey;
    }

    public String findEmailByToken(String token) {
        verifyToken(token);
        Claims claims = parsedToken(token);
        return (String) claims.get("email");
    }

    private Claims parsedToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    private void verifyToken(String token) {
        if (isNull(token)) {
            throw new NotValidTokenException(ErrorCode.NOT_VALID_TOKEN);
        }
    }

}
