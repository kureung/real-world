package com.example.realworld.jwt;

import io.jsonwebtoken.JwtException;
import lombok.Getter;

import java.security.*;

@Getter
public class JwtConfig {

    private final PublicKey publicKey;

    private final PrivateKey privateKey;

    private JwtConfig() {
        this(publicKey(), privateKey());
    }

    private JwtConfig(PublicKey publicKey, PrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    private static PublicKey publicKey() {
        return keyPair().getPublic();
    }

    private static KeyPair keyPair() {
        KeyPairGenerator kpg = null;
        try {
            kpg = KeyPairGenerator.getInstance("RSA");

        } catch (NoSuchAlgorithmException e) {
            throw new JwtException(e.getMessage());
        }

        kpg.initialize(1024);
        return kpg.generateKeyPair();
    }

    private static PrivateKey privateKey() {
        return keyPair().getPrivate();
    }

    public static JwtConfig instance() {
        return JwtConfigHolder.INSTANCE;
    }

    private static class JwtConfigHolder {

        private static final JwtConfig INSTANCE = new JwtConfig();

    }
    
}
