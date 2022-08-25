package com.example.realworld.jwt.config.secretkey;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import org.apache.tomcat.util.codec.binary.Base64;

public final class JwtSecretKey implements PrivateKey {

    private final PrivateKey privateKey;

    public JwtSecretKey(String secretKeyValue) {
        this(createdPrivateKeyByKeyValue(secretKeyValue));
    }

    public JwtSecretKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    private static PrivateKey createdPrivateKeyByKeyValue(String keyValue) {
        try {
            return KeyFactory.getInstance("RSA")
                    .generatePrivate(pkcs8EncodedKeySpecByKeyValue(keyValue));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static KeySpec pkcs8EncodedKeySpecByKeyValue(String keyValue) {
        return new PKCS8EncodedKeySpec(encodedKeyValue(keyValue));
    }

    private static byte[] encodedKeyValue(String keyValue) {
        return Base64.encodeBase64(keyValue.getBytes());
    }

    @Override
    public String getAlgorithm() {
        return privateKey.getAlgorithm();
    }

    @Override
    public String getFormat() {
        return privateKey.getFormat();
    }

    @Override
    public byte[] getEncoded() {
        return privateKey.getEncoded();
    }

    public PrivateKey privateKey() {
        return privateKey;
    }

}
