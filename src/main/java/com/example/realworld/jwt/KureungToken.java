package com.example.realworld.jwt;

public class KureungToken {

    private final String value;

    public KureungToken(String value) {
        this.value = value;
    }

//    public String userEmail(JwtConfig jwtConfig) {
//        return (String) Jwts.parser()
//                .setSigningKey(jwtConfig.secretKeyValue())
//                .parseClaimsJws(value)
//                .getBody().get("email");
//    }

    public String value() {
        return value;
    }

}
