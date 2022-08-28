package com.example.realworld.jwt;

import org.junit.jupiter.api.Test;

class JwtConfigTest {

    private final JwtConfig jwtConfig = JwtConfig.instance();

    @Test
    void name() {
        System.out.println("jwtConfig = " + jwtConfig);
        System.out.println("jwtConfig.getPrivateKey() = " + jwtConfig.getPrivateKey());
        System.out.println("jwtConfig.getPublicKey() = " + jwtConfig.getPublicKey());
    }
}