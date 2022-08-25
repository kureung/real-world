package com.example.realworld.jwt;

import com.example.realworld.domain.user.model.User;
import com.example.realworld.jwt.config.JwtConfig;
import com.example.realworld.jwt.config.JwtKureungConfig;

public class TokenMom {

    private final JwtConfig jwtConfig;
    private final User user;

    public TokenMom(JwtKureungConfig jwtKureungConfig, User user) {
        jwtConfig = jwtKureungConfig;
        this.user = user;
    }

    public KureungToken token() {
//        LocalDateTime issuanceTime = LocalDateTime.now();
//        return new KureungToken(Jwts.builder()
//                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)  // 헤더 타입 지정. jwt를 사용하기 때문에 HEADER.JWT_TYPE로 사용한다.
//                .setIssuedAt(java.sql.Date.from(issuanceTime.atZone(ZoneId.systemDefault()).toInstant()))  // 토큰 발급 시간 설정
//                .setExpiration(Date.from(issuanceTime.plusMinutes(jwtConfig.expiryTimeValue())  // 토큰 만료 시간 설정
//                        .atZone(ZoneId.systemDefault()).toInstant()))
//                .claim("email", user.email())  // 비공개 클레임 설정
//                .signWith(SignatureAlgorithm.HS512, jwtConfig.secretKeyValue())  // 해싱 알고리즘과 시크릿 키 설정
//                .compact());
        return null;
    }
    
}
