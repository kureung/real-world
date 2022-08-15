package com.example.realworld.jwt;

import com.example.realworld.web.token.TokenFactory;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenFactory implements TokenFactory {

    private final String secretKey;

    private final long expTime;

    private final String issuer;

    public JwtTokenFactory(@Value("${jwt.token.expTime}") long expTime,
                           @Value("${jwt.token.secret-key}") String secretKey,
                           @Value("${jwt.token.issuer}") String issuer
    ) {
        this.expTime = expTime * 60;
        this.secretKey = secretKey;
        this.issuer = issuer;
    }

    public String createdTokenByEmail(String userEmail) {
        LocalDateTime currentTime = LocalDateTime.now();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)  // 헤더 타입 지정. jwt를 사용하기 때문에 HEADER.JWT_TYPE로 사용한다.
                .setIssuer(issuer)  // 토큰 발급자
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))  // 토큰 발급 시간 설정
                .setExpiration(Date.from(currentTime.plusMinutes(expTime)  // 토큰 만료 시간 설정
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .claim("email", userEmail)  // 비공개 클레임 설정
                .signWith(SignatureAlgorithm.HS512, secretKey)  // 해싱 알고리즘과 시크릿 키 설정
                .compact();
    }

}
